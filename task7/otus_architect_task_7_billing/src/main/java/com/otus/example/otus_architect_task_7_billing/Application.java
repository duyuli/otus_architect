package com.otus.example.otus_architect_task_7_billing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws IOException {
    SpringApplication application = new SpringApplication(Application.class);

    System.out.println("Work dir: " + new File(".").getAbsolutePath());

    Properties dbProperties = new Properties();
    dbProperties.load(new FileInputStream("./config/application.properties"));
    dbProperties.put("spring.datasource.username", System.getenv("dbuser"));
    dbProperties.put("spring.datasource.password", System.getenv("dbpswd"));

    Properties otherProperties = new Properties();
    otherProperties
        .load(Application.class.getClassLoader().getResourceAsStream("application.properties"));
    otherProperties.putAll(dbProperties);

    application.setDefaultProperties(otherProperties);

    System.out.println("Properties loaded: " + otherProperties);

    application.run(args);
  }
}
