package com.otus.example.otus_architect_task_7_notification.controller;

import com.otus.example.otus_architect_task_7_notification.model.NotificationEntity;
import com.otus.example.otus_architect_task_7_notification.service.NotificationService;
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
  private NotificationService notificationService;

  @GetMapping("/notification/all")
  public List<NotificationEntity> getAllNotifications() {
    log.info("Getting all notifications");
    Iterable<NotificationEntity> resultIterable = notificationService.findAll();
    ArrayList<NotificationEntity> result = new ArrayList<>();
    resultIterable.forEach(result::add);
    return result;
  }
}
