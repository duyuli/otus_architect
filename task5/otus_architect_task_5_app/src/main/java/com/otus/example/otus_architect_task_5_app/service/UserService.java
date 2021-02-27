package com.otus.example.otus_architect_task_5_app.service;

import com.otus.example.otus_architect_task_5_app.model.UserEntity;
import com.otus.example.otus_architect_task_5_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class UserService {

  @Autowired
  private
  UserRepository userRepository;

  public UserEntity getUserById(int id) {
    return userRepository.findById(id).orElse(null);
  }

  public void saveOrUpdate(UserEntity userEntity) {
    userRepository.save(userEntity);
  }


}
