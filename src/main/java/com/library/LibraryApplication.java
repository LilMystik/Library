package com.library;

import com.library.exception.MyExceptionHandler;
import config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@MyExceptionHandler
@SpringBootApplication
@EnableWebMvc
@Import(SwaggerConfig.class)
public class LibraryApplication {
  public static void main(String[] args) {
    SpringApplication.run(LibraryApplication.class, args);
  }

}
