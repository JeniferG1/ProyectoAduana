package com.proyectoaduana.mstramitefronterizo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsTramitefronterizoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsTramitefronterizoApplication.class, args);
    }

}
