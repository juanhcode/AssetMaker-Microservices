package com.assetmaker.msvc.activosportafolios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcActivosPortafoliosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcActivosPortafoliosApplication.class, args);
	}

}
