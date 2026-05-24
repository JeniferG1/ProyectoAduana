package com.proyectoaduana.msvehiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsVehiculoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsVehiculoApplication.class, args);
    }

}
