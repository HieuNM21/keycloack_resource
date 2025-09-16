package com.bkav.keycloackresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KeycloackResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloackResourceApplication.class, args);
    }

}
