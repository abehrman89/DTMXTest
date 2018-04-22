package com.dtmxtest.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtmxtest.model.Employee;
import com.dtmxtest.repo.EmployeeRepository;


@RestController
public class WebController {
	@Autowired
	EmployeeRepository repository;
	
	//initial db seed - WORKING
	@RequestMapping("/save")
	public String process(){
		
		// save a list of Customers
		repository.saveAll(Arrays.asList(new Employee("Alex", "Behrman", "alex@gmail.com"), new Employee("Emily", "Davitt", "emily@gmail.com"),
										new Employee("Cal", "Notman", "cal@gmail.com"), new Employee("Shashank", "Singh", "shashank@gmail.com")));
		
		return "Done";
	}
	
	//read all - WORKING
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String findAll(){
		String result = "";
		
		for(Employee emp : repository.findAll()){
			result += emp.toString() + "<br>";
		}
		
		return result;
	}
	
	//read by last name - WORKING
    @RequestMapping(value = "/{lastName}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployee(@PathVariable("lastName") String lastName) {
    	Employee e = repository.findByLastName(lastName);
    	return e;
    }
    
	//create new - WORKING
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public String createEmployee(@RequestBody Employee e) {
		repository.save(new Employee(e.getFirstName(), e.getLastName(), e.getEmail()));
		return "Done";
	}

	//delete
	@RequestMapping(value = "/{lastName}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("lastName") String lastName) {
		repository.deleteByLastName(lastName);
	}
	
}

