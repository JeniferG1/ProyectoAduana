package com.proyectoaduana.msusuariosistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsUsuariosistemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUsuariosistemaApplication.class, args);
    }

}
