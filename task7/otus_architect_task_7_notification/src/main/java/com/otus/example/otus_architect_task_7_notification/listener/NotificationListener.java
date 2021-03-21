package com.otus.example.otus_architect_task_7_notification.listener;

import com.otus.example.otus_architect_task_7_model.NotificationEvent;
import com.otus.example.otus_architect_task_7_notification.model.NotificationEntity;
import com.otus.example.otus_architect_task_7_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

  @Autowired
  private NotificationService notificationService;

  @KafkaListener(topicPattern = "${kafka.topics.notification}", autoStartup = "${kafka.enabled}")
  public void listenToNotifications(ConsumerRecord<String, NotificationEvent> record) {
    log.info("Request for notification received: " + record.toString());

    NotificationEvent payload = record.value();
    NotificationEntity newNotification = new NotificationEntity(payload.getOrderId(),
        payload.getUserId(), payload.getMessage());
    notificationService.saveOrUpdate(newNotification);
  }
}
