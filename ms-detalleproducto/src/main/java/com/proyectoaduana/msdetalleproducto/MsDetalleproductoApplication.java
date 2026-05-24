package com.proyectoaduana.msdetalleproducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsDetalleproductoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDetalleproductoApplication.class, args);
    }

}
