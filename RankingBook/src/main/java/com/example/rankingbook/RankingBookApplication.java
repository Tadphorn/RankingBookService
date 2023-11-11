package com.example.rankingbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class RankingBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(RankingBookApplication.class, args);
		System.out.println("start service");
	}

}
