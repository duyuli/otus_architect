package com.otus.example.otus_architect_task_5_app.controller;

import com.otus.example.otus_architect_task_5_app.model.UserEntity;
import com.otus.example.otus_architect_task_5_app.model.UserEntityDTO;
import com.otus.example.otus_architect_task_5_app.model.UserInfoDTO;
import com.otus.example.otus_architect_task_5_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//mark class as Controller
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users/me")
  private UserInfoDTO getData(@RequestHeader("X-User") String userid) {

    UserEntity userEntity = userService.getUserById(Integer.valueOf(userid));
    if (userEntity == null) {

      return new UserInfoDTO(false, null);
    }
    return new UserInfoDTO(true,
        new UserEntityDTO(userEntity.getFirstName(), userEntity.getLastName()));
  }

  @PostMapping("/users/me")
  public ResponseEntity updateData(@RequestHeader("X-User") String userid,
      @RequestBody UserEntityDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserid(Integer.valueOf(userid));
    userEntity.setFirstName(userDTO.getFirstName());
    userEntity.setLastName(userDTO.getLastName());
    userService.saveOrUpdate(userEntity);
    return new ResponseEntity(HttpStatus.OK);
  }

}
