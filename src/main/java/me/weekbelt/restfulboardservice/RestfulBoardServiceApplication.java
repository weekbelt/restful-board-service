package me.weekbelt.restfulboardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestfulBoardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulBoardServiceApplication.class, args);
    }

}
