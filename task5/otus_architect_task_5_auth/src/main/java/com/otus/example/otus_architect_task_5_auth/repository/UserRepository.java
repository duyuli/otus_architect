package com.otus.example.otus_architect_task_5_auth.repository;

import com.otus.example.otus_architect_task_5_auth.model.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

  Optional<UserEntity> findByLogin(String login);

  Optional<UserEntity> findBySession(String session);

}
