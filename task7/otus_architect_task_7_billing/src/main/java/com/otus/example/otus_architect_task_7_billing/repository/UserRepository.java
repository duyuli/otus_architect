package com.otus.example.otus_architect_task_7_billing.repository;

import com.otus.example.otus_architect_task_7_billing.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
