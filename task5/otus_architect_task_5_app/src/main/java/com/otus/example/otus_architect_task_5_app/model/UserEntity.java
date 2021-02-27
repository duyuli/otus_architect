package com.otus.example.otus_architect_task_5_app.model;

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
  private int userid;
  @Column
  private String firstName;
  @Column
  private String lastName;

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

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

}
