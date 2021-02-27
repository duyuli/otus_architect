package com.otus.example.otus_architect_task_5_app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtusArchitectTask5Auth {

  public static void main(String[] args) throws IOException {
    SpringApplication application = new SpringApplication(OtusArchitectTask5Auth.class);

    System.out.println("Work dir: " + new File(".").getAbsolutePath());

    Properties properties = new Properties();
    properties.load(new FileInputStream("./config/application.properties"));
    properties.put("spring.datasource.username", System.getenv("dbuser"));
    properties.put("spring.datasource.password", System.getenv("dbpswd"));
    application.setDefaultProperties(properties);

    System.out.println("Properties loaded: " + properties);

    application.run(args);
  }

}
