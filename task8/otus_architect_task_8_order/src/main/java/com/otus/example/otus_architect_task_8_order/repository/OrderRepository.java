package com.otus.example.otus_architect_task_8_order.repository;

import com.otus.example.otus_architect_task_8_order.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

}
