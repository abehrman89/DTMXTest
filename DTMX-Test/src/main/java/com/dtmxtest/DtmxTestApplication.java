package com.dtmxtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dtmxtest.repo.EmployeeRepository;

@SpringBootApplication
public class DtmxTestApplication implements CommandLineRunner{

	@Autowired
	EmployeeRepository repository;
	
	public static void main(String[] args){
		SpringApplication.run(DtmxTestApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		repository.deleteAll();
	}
}
