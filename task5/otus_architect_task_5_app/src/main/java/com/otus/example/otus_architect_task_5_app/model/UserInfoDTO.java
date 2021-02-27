package com.otus.example.otus_architect_task_5_app.model;

public class UserInfoDTO {

  private boolean found;
  private UserEntityDTO user;

  public UserInfoDTO() {
  }

  public UserInfoDTO(boolean found, UserEntityDTO user) {
    this.found = found;
    this.user = user;
  }

  public boolean isFound() {
    return found;
  }

  public void setFound(boolean found) {
    this.found = found;
  }

  public UserEntityDTO getUser() {
    return user;
  }

  public void setUser(UserEntityDTO user) {
    this.user = user;
  }
}
