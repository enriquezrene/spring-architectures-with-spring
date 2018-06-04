package com.packpub.simplebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SimpleBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBatchApplication.class, args);
	}
}
