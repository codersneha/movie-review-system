package com.example.helloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}


/**
 *
 *
 * Student portal
 * Create student
 * I should be able to add marks per semester per subject
 * Student should have marks per semester
 * SHow me a result, it can be per semseter or it can be overall or it can be per year
 * Give flexibility to Update student name, marks
 *
 * while creating a student take input for branch

 *  At backend keep branch to subject mapping
 */