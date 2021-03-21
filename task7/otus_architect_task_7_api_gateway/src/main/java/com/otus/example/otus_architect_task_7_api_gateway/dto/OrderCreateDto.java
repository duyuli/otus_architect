package com.otus.example.otus_architect_task_7_api_gateway.dto;

public class OrderCreateDto {

  private String userId;
  private String orderId;
  private int price;

  public OrderCreateDto() {
  }

  public OrderCreateDto(String userId, String orderId, int price) {
    this.userId = userId;
    this.orderId = orderId;
    this.price = price;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "OrderCreateDto{" +
        "userId='" + userId + '\'' +
        ", orderId='" + orderId + '\'' +
        ", price=" + price +
        '}';
  }
}
