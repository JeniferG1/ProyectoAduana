package com.proyectoaduana.mspasofronterizo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPasofronterizoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPasofronterizoApplication.class, args);
    }

}
