package com.example.bubbleskhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BubbleskhuApplication {

	public static void main(String[] args) {
		SpringApplication.run(BubbleskhuApplication.class, args);
	}

}
