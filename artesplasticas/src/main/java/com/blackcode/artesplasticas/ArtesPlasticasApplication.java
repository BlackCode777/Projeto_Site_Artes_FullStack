package com.blackcode.artesplasticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blackcode.artesplasticas", "com.blackcode.artesplasticas.config"})
public class ArtesPlasticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtesPlasticasApplication.class, args);
	}

}
