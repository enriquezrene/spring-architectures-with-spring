package com.packtpub.demospringbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class DemoSpringBatchApplication {

	public static void main(String[] args) {
//		System.setProperty("input", "file://" + new File("/Users/jlong/Desktop/in.csv").getAbsolutePath());
//		System.setProperty("output", "file://" + new File("/Users/jlong/Desktop/out.csv").getAbsolutePath());
		SpringApplication.run(DemoSpringBatchApplication.class, args);
	}
}
