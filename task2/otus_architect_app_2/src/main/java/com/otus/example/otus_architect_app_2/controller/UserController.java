package com.otus.example.otus_architect_app_2.controller;

import com.otus.example.otus_architect_app_2.model.UserEntity;
import com.otus.example.otus_architect_app_2.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//mark class as Controller
@RestController
public class UserController {

  //autowire the UsersService class
  @Autowired
  private UserService userService;

  //creating a get mapping that retrieves all the users detail from the database
  @GetMapping("/user")
  private List<UserEntity> getAllUsers() {
    return userService.getAllUsers();
  }

  //creating a get mapping that retrieves the detail of a specific user
  @GetMapping("/user/{userid}")
  private UserEntity getUser(@PathVariable("userid") int userid) {
    return userService.getUserById(userid);
  }

  //creating a delete mapping that deletes a specified user
  @DeleteMapping("/user/{userid}")
  private void deleteUser(@PathVariable("userid") int userid) {
    userService.delete(userid);
  }

  //creating post mapping that post the userEntity detail in the database
  @PostMapping("/user")
  private int saveUser(@RequestBody UserEntity userEntity) {
    userService.saveOrUpdate(userEntity);
    return userEntity.getUserid();
  }

  //creating put mapping that updates the userEntity detail
  @PutMapping("/user")
  private UserEntity update(@RequestBody UserEntity userEntity) {
    userService.saveOrUpdate(userEntity);
    return userEntity;
  }

}
