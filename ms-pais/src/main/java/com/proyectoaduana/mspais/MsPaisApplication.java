package com.proyectoaduana.mspais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPaisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPaisApplication.class, args);
    }

}
