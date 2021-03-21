package com.otus.example.otus_architect_task_7_order.listener;

import com.otus.example.otus_architect_task_7_model.BillingEvent;
import com.otus.example.otus_architect_task_7_model.NotificationEvent;
import com.otus.example.otus_architect_task_7_model.OrderEvent;
import com.otus.example.otus_architect_task_7_order.config.KafkaProperties;
import com.otus.example.otus_architect_task_7_order.model.OrderEntity;
import com.otus.example.otus_architect_task_7_order.service.OrderService;
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
public class OrderListener {

  @Autowired
  private KafkaTemplate<String, NotificationEvent> kakfaProducerNotification;
  @Autowired
  private KafkaTemplate<String, BillingEvent> kakfaProducerBilling;
  @Autowired
  private KafkaProperties kafkaProperties;
  @Autowired
  private OrderService orderService;

  @KafkaListener(topicPattern = "${kafka.topics.order}", autoStartup = "${kafka.enabled}")
  public void listenToOrderEvents(ConsumerRecord<String, OrderEvent> record) {
    log.info("Order event received: " + record.toString());

    OrderEvent payload = record.value();
    switch (payload.getCommand()) {
      case "create":
        OrderEntity newOrder = new OrderEntity(payload.getOrderId(),
            payload.getContext().get("userId"), Integer.valueOf(payload.getContext().get("price")),
            "pending");
        orderService.saveOrUpdate(newOrder);

        Map<String, String> context = new HashMap<>();
        context.put("orderId", newOrder.getOrderId());
        context.put("price", String.valueOf(newOrder.getPrice()));
        BillingEvent billingEvent = new BillingEvent("pay",
            newOrder.getUserId(), context);
        log.info("Sending to kafka: " + billingEvent.toString());
        kakfaProducerBilling.send(kafkaProperties.getTopics().getBilling(),
            String.valueOf(newOrder.getUserId()), billingEvent);
        break;
      case "processed": {
        Optional<OrderEntity> optional = orderService.getById(payload.getOrderId());
        if (optional.isPresent()) {
          OrderEntity orderEntity = optional.get();
          orderEntity.setStatus(payload.getContext().get("result"));
          orderService.saveOrUpdate(orderEntity);

          NotificationEvent notificationEvent = new NotificationEvent(orderEntity.getOrderId(),
              orderEntity.getUserId(), orderEntity.getStatus());
          log.info("Sending to kafka: " + notificationEvent.toString());
          kakfaProducerNotification.send(kafkaProperties.getTopics().getNotification(),
              String.valueOf(orderEntity.getUserId()), notificationEvent);

        }
        break;
      }

    }
  }
}
