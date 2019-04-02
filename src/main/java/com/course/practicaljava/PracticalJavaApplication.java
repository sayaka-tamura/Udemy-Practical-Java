package com.course.practicaljava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticalJavaApplication {
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(PracticalJavaApplication.class); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(PracticalJavaApplication.class, args);
	}
}
