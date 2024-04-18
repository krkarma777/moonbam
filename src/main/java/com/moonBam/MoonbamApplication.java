package com.moonBam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoonbamApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonbamApplication.class, args);
    }

}