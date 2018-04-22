package com.dtmxtest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtmxtest.model.Employee;
import com.dtmxtest.repo.EmployeeRepository;


@RestController
public class WebController {
	@Autowired
	EmployeeRepository repository;
	
	@RequestMapping("/save")
	public String process(){
		repository.saveAll(Arrays.asList(new Employee("Alex", "Behrman", "alex@gmail.com"), new Employee("Emily", "Davitt", "emily@gmail.com"),
										new Employee("Cal", "Notman", "cal@gmail.com"), new Employee("Shashank", "Singh", "shashank@gmail.com")));
		return "Done";
	}
	
	/**
	 * get list of all employees form db
	 * @return elist object
	 */
	@GetMapping("/")
	public List<Employee> getAll() {
		List<Employee> elist = (List<Employee>) repository.findAll();
		return elist;
	}
    
	/**
	 * get specific employee from db by last name
	 * @param lastName
	 * @return employee object
	 */
    @GetMapping("/{lastName}")
    public Employee getByLastName(@PathVariable String lastName) {
    	Employee e1 = repository.findByLastName(lastName);
    	if (e1 == null) {
    		throw new NoSuchElementException("No employee with that last name to get");
    	}
    	return e1;
    }
    
    /**
     * add new employee to db
     * @param e
     * @return new employee object
     */
    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee e) {
		Employee e2 = new Employee(e.getFirstName(), e.getLastName(), e.getEmail());
		repository.save(e2);
    	return e2;
    }
    
    /**
     * update info for current employee
     * @param lastName
     * @param e
     * @return updated employee object
     */
    @PutMapping("/{lastName}")
    public Employee updateEmployee(@PathVariable String lastName, @RequestBody Employee e) {
    	Employee e3 = repository.findByLastName(lastName);
    	if (e3 == null) {
    		throw new NoSuchElementException("No employee with that last name to update");
    	}
    	
    	if (e.getFirstName() != null) {
    		e3.setFirstName(e.getFirstName());
    	}
    	if (e.getLastName() != null) {
    		e3.setLastName(e.getLastName());
    	}
    	if (e.getEmail() != null) {
    		e3.setEmail(e.getEmail());
    	}
    	
    	repository.save(e3);
    	return e3;
    }
    
    /**
     * delete employee from db
     * @param lastName
     * @return string confirming employee deletion
     */
    @DeleteMapping("/{lastName}")
    public String deleteEmployee(@PathVariable String lastName) {
		Employee e4 = repository.findByLastName(lastName);
    	if (e4 == null) {
    		throw new NoSuchElementException("No employee with that last name to delete");
    	}
    	
		repository.delete(e4);
		return "Employee " + e4.getFirstName() + " " + e4.getLastName() + " deleted";
    }
	
}

