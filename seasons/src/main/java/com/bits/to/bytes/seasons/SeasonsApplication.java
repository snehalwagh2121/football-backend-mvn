package com.bits.to.bytes.seasons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SeasonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeasonsApplication.class, args);
	}
}
