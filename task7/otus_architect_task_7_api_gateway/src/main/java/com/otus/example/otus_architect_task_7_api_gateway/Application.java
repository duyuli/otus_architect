package com.otus.example.otus_architect_task_7_api_gateway;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws IOException {
    SpringApplication application = new SpringApplication(Application.class);
    application.run(args);
  }
}
