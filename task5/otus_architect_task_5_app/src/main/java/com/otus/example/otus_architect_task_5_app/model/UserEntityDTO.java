package com.otus.example.otus_architect_task_5_app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

public class UserEntityDTO {

  private String firstName;
  private String lastName;

  public UserEntityDTO() {
  }

  public UserEntityDTO(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
