package com.otus.example.otus_architect_task_7_order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
public class OrderEntity {

  //Defining user id as primary key
  @Id
  private String orderId;
  @Column
  private String userId;
  @Column
  private int price;
  @Column
  private String status;

  public OrderEntity() {
  }

  public OrderEntity(String orderId, String userId, int price, String status) {
    this.orderId = orderId;
    this.userId = userId;
    this.price = price;
    this.status = status;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "OrderEntity{" +
        "orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", price=" + price +
        ", status='" + status + '\'' +
        '}';
  }
}
