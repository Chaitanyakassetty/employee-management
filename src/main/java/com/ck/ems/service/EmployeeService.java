package com.ck.ems.service;

//import java.util.List;

import org.springframework.data.domain.Page;

import com.ck.ems.dto.EmployeeRequest;
import com.ck.ems.dto.EmployeeResponse;

public interface EmployeeService {
	
	EmployeeResponse createEmployee(EmployeeRequest request);
	
	EmployeeResponse getEmployeeById(Long id);
	
//	List<EmployeeResponse> getAllEmployees();
	
//	Now return Page<> instead of List	
	Page<EmployeeResponse> getAllEmployees(int page, int size, String sortBy, String direction );
	
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
	
	void deleteEmployee(Long id);
	
}

