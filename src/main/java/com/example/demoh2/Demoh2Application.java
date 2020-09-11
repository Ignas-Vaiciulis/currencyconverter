package com.example.demoh2;


import com.example.demoh2.service.TaskSheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class Demoh2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demoh2Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(TaskSheduler taskSheduler) {
		return args -> {
			taskSheduler.retrieveCurrencies();
		};
	}
}

