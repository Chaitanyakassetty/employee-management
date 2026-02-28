package com.ck.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ems.dto.ApiResponse;
import com.ck.ems.dto.EmployeeRequest;
import com.ck.ems.dto.EmployeeResponse;
import com.ck.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{
	
	private static final Logger logger =
	        LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
		
		logger.info("Received request to create employee ");
		
		EmployeeResponse response = employeeService.createEmployee(employeeRequest);
		
		logger.info("Sending response for employee creation");

		ApiResponse<EmployeeResponse> apiResponse = 
				new ApiResponse<>(201, "Employee created successfully", response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>>  getEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest ) {
		
		EmployeeResponse response = employeeService.getEmployeeById(id);

		    ApiResponse<EmployeeResponse> apiResponse =
		            new ApiResponse<>(200, "Employee fetched successfully", response);

//		return ResponseEntity.ok(employeeService.getEmployeeById(id));
		    return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> getAllEmployees(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		
		Page<EmployeeResponse> employees = employeeService.getAllEmployees(page, size, sortBy, direction);
		
		 ApiResponse<Page<EmployeeResponse>> apiResponse =
		            new ApiResponse<>(200, "Employees fetched successfully", employees);

//			return ResponseEntity.ok(employees);
		    return ResponseEntity.ok(apiResponse);
				
			}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequest employeeRequest) {
		return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
		 employeeService.deleteEmployee(id);
		 
		 ApiResponse<String> response =
		            new ApiResponse<>(200, "Employee deleted successfully", null);
		 
		 //return ResponseEntity.ok("Employee deleted successfully");
		 return ResponseEntity.ok(response);
	}
}