package com.otus.example.otus_architect_task_8_api_gateway.dto;

public class UserDepositDto {

  private String userId;
  private int amount;

  public UserDepositDto() {
  }

  public UserDepositDto(String userId, int amount) {
    this.userId = userId;
    this.amount = amount;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "UserDepositDto{" +
        "userId='" + userId + '\'' +
        ", amount=" + amount +
        '}';
  }
}
