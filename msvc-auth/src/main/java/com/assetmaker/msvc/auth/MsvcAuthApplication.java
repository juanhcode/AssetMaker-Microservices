package com.assetmaker.msvc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MsvcAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcAuthApplication.class, args);
	}

}
