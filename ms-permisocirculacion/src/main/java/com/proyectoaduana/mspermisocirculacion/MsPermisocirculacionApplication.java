package com.proyectoaduana.mspermisocirculacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPermisocirculacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPermisocirculacionApplication.class, args);
    }

}
