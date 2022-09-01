package com.bits.to.bytes.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsApplication.class, args);
	}
//todo- scheduler which will always fetch atleast 20 teams desc from ext api according to the most searched for leagues done in past 2 weeks.
}
