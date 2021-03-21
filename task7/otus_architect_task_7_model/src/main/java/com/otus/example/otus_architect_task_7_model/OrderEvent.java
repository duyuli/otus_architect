package com.otus.example.otus_architect_task_7_model;

import java.util.Map;

public class OrderEvent {

  private String command;
  private String orderId;
  private Map<String, String> context;

  public OrderEvent() {
  }

  public OrderEvent(String command, String orderId,
      Map<String, String> context) {
    this.command = command;
    this.orderId = orderId;
    this.context = context;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Map<String, String> getContext() {
    return context;
  }

  public void setContext(Map<String, String> context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return "OrderEvent{" +
        "command='" + command + '\'' +
        ", orderId='" + orderId + '\'' +
        ", context=" + context +
        '}';
  }
}
