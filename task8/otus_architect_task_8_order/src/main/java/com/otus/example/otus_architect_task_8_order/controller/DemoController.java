package com.otus.example.otus_architect_task_8_order.controller;

import com.otus.example.otus_architect_task_8_order.model.OrderEntity;
import com.otus.example.otus_architect_task_8_order.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class DemoController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/order/all")
  public List<OrderEntity> getAllOrders() {
    log.info("Getting all orders");
    Iterable<OrderEntity> resultIterable = orderService.findAll();
    ArrayList<OrderEntity> result = new ArrayList<>();
    resultIterable.forEach(result::add);
    return result;
  }
}
