package com.otus.example.otus_architect_task_7_billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
public class UserEntity {

  //Defining user id as primary key
  @Id
  private String userId;
  @Column
  private String username;
  @Column
  private int balance;

  public UserEntity() {
  }

  public UserEntity(String userId, String username, int balance) {
    this.userId = userId;
    this.username = username;
    this.balance = balance;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "userId='" + userId + '\'' +
        ", username='" + username + '\'' +
        ", balance=" + balance +
        '}';
  }
}
