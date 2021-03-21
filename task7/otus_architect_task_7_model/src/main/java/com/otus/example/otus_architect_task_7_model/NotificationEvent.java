package com.otus.example.otus_architect_task_7_model;

public class NotificationEvent {

  private String orderId;
  private String userId;
  private String message;

  public NotificationEvent() {
  }

  public NotificationEvent(String orderId, String userId, String message) {
    this.orderId = orderId;
    this.userId = userId;
    this.message = message;
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "NotificationEvent{" +
        "orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
