package com.otus.example.otus_architect_task_7_billing.listener;

import com.otus.example.otus_architect_task_7_billing.config.KafkaProperties;
import com.otus.example.otus_architect_task_7_billing.model.UserEntity;
import com.otus.example.otus_architect_task_7_billing.service.UserService;
import com.otus.example.otus_architect_task_7_model.BillingEvent;
import com.otus.example.otus_architect_task_7_model.OrderEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BillingListener {

  @Autowired
  private KafkaTemplate<String, OrderEvent> kakfaProducerOrder;
  @Autowired
  private KafkaProperties kafkaProperties;
  @Autowired
  private UserService userService;

  @KafkaListener(topicPattern = "${kafka.topics.billing}", autoStartup = "${kafka.enabled}")
  public void listenToBillingEvents(ConsumerRecord<String, BillingEvent> record) {
    log.info("Billing event received: " + record.toString());

    BillingEvent payload = record.value();
    switch (payload.getCommand()) {
      case "create":
        UserEntity newUser = new UserEntity(payload.getUserId(),
            payload.getContext().get("username"),
            0);
        userService.saveOrUpdate(newUser);
        break;
      case "deposit": {
        Optional<UserEntity> optional = userService.getById(payload.getUserId());
        if (optional.isPresent()) {
          UserEntity userEntity = optional.get();
          userEntity.setBalance(
              userEntity.getBalance() + Integer.valueOf(payload.getContext().get("amount")));
          userService.saveOrUpdate(userEntity);
        }
        break;
      }
      case "pay": {
        Optional<UserEntity> optional = userService.getById(payload.getUserId());
        if (optional.isPresent()) {
          UserEntity userEntity = optional.get();
          int price = Integer.valueOf(payload.getContext().get("price"));
          boolean success = false;
          if (userEntity.getBalance() > price) {
            userEntity.setBalance(userEntity.getBalance() - price);
            userService.saveOrUpdate(userEntity);
            success = true;
          }

          Map<String, String> context = new HashMap<>();
          context.put("result", success ? "success" : "declined");
          OrderEvent orderEvent = new OrderEvent("processed", payload.getContext().get("orderId"),
              context);
          log.info("Sending to kafka: " + orderEvent.toString());
          kakfaProducerOrder.send(kafkaProperties.getTopics().getOrder(),
              String.valueOf(payload.getUserId()), orderEvent);

        }

        break;
      }
    }
  }
}
