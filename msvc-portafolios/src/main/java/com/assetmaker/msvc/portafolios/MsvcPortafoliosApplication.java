package com.assetmaker.msvc.portafolios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.assetmaker.msvc.portafolios.clients")
public class MsvcPortafoliosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcPortafoliosApplication.class, args);
    }
}
