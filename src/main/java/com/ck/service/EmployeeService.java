package com.ck.service;

import java.util.List;

import com.ck.entity.Employee;

public interface EmployeeService {
	
	Employee createEmployee (Employee employee);
	
	Employee getEmployeeById(Long id);
	
	List<Employee> getAllEmployees();
	
	Employee updateEmployee(Long Id, Employee employee);
	
	void deleteEmployee(Long id);
	
}

