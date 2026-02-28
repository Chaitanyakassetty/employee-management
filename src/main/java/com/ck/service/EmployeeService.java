package com.ck.service;

import java.util.List;

import com.ck.dto.EmployeeRequest;
import com.ck.dto.EmployeeResponse;

public interface EmployeeService {
	
	EmployeeResponse createEmployee(EmployeeRequest request);
	
	EmployeeResponse getEmployeeById(Long id);
	
	List<EmployeeResponse> getAllEmployees();

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
	
	void deleteEmployee(Long id);
	
}

