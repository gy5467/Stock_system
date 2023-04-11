package org.koreait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@SpringBootApplication
public class Exam03Application {

	public static void main(String[] args) {
		SpringApplication.run(Exam03Application.class, args);
	}
}
