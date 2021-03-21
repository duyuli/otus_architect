package com.otus.example.otus_architect_task_7_model;

import java.util.Map;

public class BillingEvent {

  private String command;
  private String userId;
  private Map<String, String> context;

  public BillingEvent() {
  }

  public BillingEvent(String command, String userId,
      Map<String, String> context) {
    this.command = command;
    this.userId = userId;
    this.context = context;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Map<String, String> getContext() {
    return context;
  }

  public void setContext(Map<String, String> context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return "BillingEvent{" +
        "command='" + command + '\'' +
        ", userId='" + userId + '\'' +
        ", context=" + context +
        '}';
  }
}
