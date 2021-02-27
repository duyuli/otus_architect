package com.otus.example.otus_architect_task_5_app.repository;

import com.otus.example.otus_architect_task_5_app.model.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
