package com.ck.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.entity.Employee;
import com.ck.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee){
		
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/{Id}")
	public Employee getEmployeeById(@PathVariable Long id, @RequestBody Employee employee ) {
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("{/Id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		 employeeService.deleteEmployee(id);
		 return "Employee deleted";
	}
}