package com.ck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.dto.EmployeeRequest;
import com.ck.dto.EmployeeResponse;
import com.ck.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
		EmployeeResponse response = employeeService.createEmployee(employeeRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response) ;
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<EmployeeResponse>  getEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest ) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PutMapping("{/Id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
		return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
		 employeeService.deleteEmployee(id);
		    return ResponseEntity.ok("Employee deleted successfully");
	}
}