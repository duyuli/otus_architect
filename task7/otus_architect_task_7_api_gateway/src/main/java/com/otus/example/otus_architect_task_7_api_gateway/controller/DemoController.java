package com.otus.example.otus_architect_task_7_api_gateway.controller;

import com.otus.example.otus_architect_task_7_api_gateway.config.KafkaProperties;
import com.otus.example.otus_architect_task_7_api_gateway.dto.OrderCreateDto;
import com.otus.example.otus_architect_task_7_api_gateway.dto.UserCreateDto;
import com.otus.example.otus_architect_task_7_api_gateway.dto.UserDepositDto;
import com.otus.example.otus_architect_task_7_model.BillingEvent;
import com.otus.example.otus_architect_task_7_model.OrderEvent;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class DemoController {

  @Autowired
  private KafkaTemplate<String, BillingEvent> kakfaProducerBilling;
  @Autowired
  private KafkaTemplate<String, OrderEvent> kakfaProducerOrder;
  @Autowired
  private KafkaProperties kafkaProperties;

  @PostMapping("/user/add")
  @ResponseStatus(HttpStatus.OK)
  public void addUser(@RequestBody UserCreateDto userCreateDto) {
    log.info("Got create user request: " + userCreateDto.toString());
    Map<String, String> context = new HashMap<>();
    context.put("username", userCreateDto.getUsername());
    BillingEvent billingEvent = new BillingEvent("create", userCreateDto.getUserId(), context);
    log.info("Sending to kafka: " + billingEvent.toString());
    kakfaProducerBilling.send(kafkaProperties.getTopics().getBilling(),
        String.valueOf(userCreateDto.getUserId()), billingEvent);
  }

  @PostMapping("/user/deposit")
  @ResponseStatus(HttpStatus.OK)
  public void depositMoney(@RequestBody UserDepositDto userDepositDto) {
    log.info("Got deposit money request: " + userDepositDto.toString());
    Map<String, String> context = new HashMap<>();
    context.put("amount", String.valueOf(userDepositDto.getAmount()));
    BillingEvent billingEvent = new BillingEvent("deposit", userDepositDto.getUserId(), context);
    log.info("Sending to kafka: " + billingEvent.toString());
    kakfaProducerBilling.send(kafkaProperties.getTopics().getBilling(),
        String.valueOf(userDepositDto.getUserId()), billingEvent);
  }

  @PostMapping("/order/create")
  @ResponseStatus(HttpStatus.OK)
  public void createOrder(@RequestBody OrderCreateDto orderCreateDto) {
    log.info("Got create order request: " + orderCreateDto.toString());
    Map<String, String> context = new HashMap<>();
    context.put("userId", orderCreateDto.getUserId());
    context.put("price", String.valueOf(orderCreateDto.getPrice()));
    OrderEvent orderEvent = new OrderEvent("create", orderCreateDto.getOrderId(), context);
    log.info("Sending to kafka: " + orderEvent.toString());
    kakfaProducerOrder.send(kafkaProperties.getTopics().getOrder(),
        String.valueOf(orderCreateDto.getUserId()), orderEvent);
  }

}
