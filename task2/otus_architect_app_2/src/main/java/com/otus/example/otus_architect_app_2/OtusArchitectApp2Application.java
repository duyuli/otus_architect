package com.otus.example.otus_architect_app_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtusArchitectApp2Application {

  public static void main(String[] args) throws IOException {
    SpringApplication application = new SpringApplication(OtusArchitectApp2Application.class);

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
