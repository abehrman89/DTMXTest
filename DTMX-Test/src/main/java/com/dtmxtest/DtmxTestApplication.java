package com.dtmxtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dtmxtest.repo.EmployeeRepository;

@SpringBootApplication
public class DtmxTestApplication {
	
	@Autowired
	EmployeeRepository repository;
	
	public static void main(String[] args){
		SpringApplication.run(DtmxTestApplication.class, args);
	}
}
