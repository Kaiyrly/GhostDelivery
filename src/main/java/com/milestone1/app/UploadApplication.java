package com.milestone1.app;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
 
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class UploadApplication {
 
  public static void main(String[] args) {
    SpringApplication.run(UploadApplication.class, args);
  }
}