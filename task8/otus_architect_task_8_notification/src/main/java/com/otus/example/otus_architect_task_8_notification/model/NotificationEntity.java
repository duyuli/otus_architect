package com.otus.example.otus_architect_task_8_notification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
public class NotificationEntity {

  //Defining user id as primary key
  @Id
  @GeneratedValue
  private int messageId;
  @Column
  private String orderId;
  @Column
  private String userId;
  @Column
  private String message;

  public NotificationEntity() {
  }

  public NotificationEntity(String orderId, String userId, String message) {
    this.orderId = orderId;
    this.userId = userId;
    this.message = message;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getMessageId() {
    return messageId;
  }

  public void setMessageId(int messageId) {
    this.messageId = messageId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "NotificationEntity{" +
        "messageId=" + messageId +
        ", orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
