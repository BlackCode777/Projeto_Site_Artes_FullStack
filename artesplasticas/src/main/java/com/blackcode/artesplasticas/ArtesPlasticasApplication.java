package com.blackcode.artesplasticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.blackcode.artesplasticas.infrastructure.adapter.out.dbPostgres")
@EnableMongoRepositories(basePackages = "com.blackcode.artesplasticas.infrastructure.adapter.out.cacheMongo")
public class ArtesPlasticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtesPlasticasApplication.class, args);
	}

}
