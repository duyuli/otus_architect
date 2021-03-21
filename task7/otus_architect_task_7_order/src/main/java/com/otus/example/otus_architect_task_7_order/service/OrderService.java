package com.otus.example.otus_architect_task_7_order.service;

import com.otus.example.otus_architect_task_7_order.model.OrderEntity;
import com.otus.example.otus_architect_task_7_order.repository.OrderRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class OrderService {

  @Autowired
  private
  OrderRepository orderRepository;

  public Iterable<OrderEntity> findAll() {
    return orderRepository.findAll();
  }

  public void saveOrUpdate(OrderEntity orderEntity) {
    orderRepository.save(orderEntity);
  }

  public Optional<OrderEntity> getById(String orderId) {
    return orderRepository.findById(orderId);
  }
}
