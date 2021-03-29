package com.otus.example.otus_architect_task_8_notification.service;

import com.otus.example.otus_architect_task_8_notification.model.NotificationEntity;
import com.otus.example.otus_architect_task_8_notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class NotificationService {

  @Autowired
  private
  NotificationRepository notificationRepository;

  public Iterable<NotificationEntity> findAll() {
    return notificationRepository.findAll();
  }

  public void saveOrUpdate(NotificationEntity notificationEntity) {
    notificationRepository.save(notificationEntity);
  }


}
