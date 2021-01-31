package com.otus.example.otus_architect_app_2.repository;

import com.otus.example.otus_architect_app_2.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

//repository that extends CrudRepository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
