package com.otus.example.otus_architect_task_7_billing.service;

import com.otus.example.otus_architect_task_7_billing.model.UserEntity;
import com.otus.example.otus_architect_task_7_billing.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class UserService {

  @Autowired
  private
  UserRepository userRepository;

  public Iterable<UserEntity> findAll() {
    return userRepository.findAll();
  }

  public void saveOrUpdate(UserEntity userEntity) {
    userRepository.save(userEntity);
  }

  public Optional<UserEntity> getById(String userId){
    return userRepository.findById(userId);
  }
}
