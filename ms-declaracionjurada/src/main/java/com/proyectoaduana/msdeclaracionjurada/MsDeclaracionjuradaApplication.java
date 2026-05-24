package com.proyectoaduana.msdeclaracionjurada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsDeclaracionjuradaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDeclaracionjuradaApplication.class, args);
    }

}
