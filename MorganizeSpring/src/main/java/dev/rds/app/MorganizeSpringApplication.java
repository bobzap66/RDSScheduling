package dev.rds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("dev.rds")
@EntityScan("dev.rds.entities")
@EnableJpaRepositories("dev.rds.repositories")

public class MorganizeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorganizeSpringApplication.class, args);
	}

}
