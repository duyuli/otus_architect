package com.otus.example.otus_architect_task_8_billing.controller;

import com.otus.example.otus_architect_task_8_billing.model.UserEntity;
import com.otus.example.otus_architect_task_8_billing.service.UserService;
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
  private UserService userService;

  @GetMapping("/user/all")
  public List<UserEntity> getAllUsers() {
    log.info("Getting all users");
    Iterable<UserEntity> resultIterable = userService.findAll();
    ArrayList<UserEntity> result = new ArrayList<>();
    resultIterable.forEach(result::add);
    return result;
  }
}
