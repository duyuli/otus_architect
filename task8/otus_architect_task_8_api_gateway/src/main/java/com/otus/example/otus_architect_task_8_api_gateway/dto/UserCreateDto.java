package com.otus.example.otus_architect_task_8_api_gateway.dto;

public class UserCreateDto {

  private String userId;
  private String username;

  public UserCreateDto() {
  }

  public UserCreateDto(String userId, String username) {
    this.userId = userId;
    this.username = username;
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

  @Override
  public String toString() {
    return "UserCreateDto{" +
        "userId='" + userId + '\'' +
        ", username='" + username + '\'' +
        '}';
  }
}
