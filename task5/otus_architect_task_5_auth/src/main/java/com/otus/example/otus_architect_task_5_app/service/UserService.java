package com.otus.example.otus_architect_task_5_app.service;

import com.otus.example.otus_architect_task_5_app.model.UserEntity;
import com.otus.example.otus_architect_task_5_app.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class UserService {

  @Autowired
  private
  UserRepository userRepository;

  public UserEntity findByLogin(String login) {
    return userRepository.findByLogin(login).orElse(null);
  }

  public UserEntity findBySession(String session) {
    return userRepository.findBySession(session).orElse(null);
  }

  //getting all users record by using the method findaAll() of CrudRepository
  public List<UserEntity> getAllUsers() {
    List<UserEntity> userEntities = new ArrayList<UserEntity>();
    userRepository.findAll().forEach(userEntities::add);
    return userEntities;
  }

  //getting a specific record by using the method findById() of CrudRepository
  public UserEntity getUserById(int id) {
    return userRepository.findById(id).orElse(null);
  }

  //saving a specific record by using the method save() of CrudRepository
  public void saveOrUpdate(UserEntity userEntity) {
    userRepository.save(userEntity);
  }

  //deleting a specific record by using the method deleteById() of CrudRepository
  public void delete(int id) {
    userRepository.deleteById(id);
  }

  //updating a record
  public void update(UserEntity userEntity) {
    userRepository.save(userEntity);
  }


}
