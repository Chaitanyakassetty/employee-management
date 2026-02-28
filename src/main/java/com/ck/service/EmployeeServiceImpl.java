package com.ck.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ck.dto.EmployeeRequest;
import com.ck.dto.EmployeeResponse;
import com.ck.entity.Employee;
import com.ck.exception.ResourceNotFoundException;
import com.ck.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
	   
		// Convert DTO → Entity
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        // Convert Entity → Response DTO
        return mapToResponse(savedEmployee);
    }

	// GET BY ID
	@Override
	public EmployeeResponse  getEmployeeById(Long id) {
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found" + id));
		return mapToResponse(employee);
	}


	// GET ALL
	@Override
	public List<EmployeeResponse> getAllEmployees() {
	     List<Employee> employees = employeeRepository.findAll();

	        return employees.stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	
	// UPDATE
	@Override
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
		
		Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		
//		Employee employee = new Employee();
        existingEmployee.setName(employeeRequest.getName());
		existingEmployee.setEmail(employeeRequest.getEmail());
		existingEmployee.setDepartment(employeeRequest.getDepartment());
	    existingEmployee.setSalary(employeeRequest.getSalary());
		
	    Employee updatedEmployee = employeeRepository.save(existingEmployee);
		return mapToResponse(updatedEmployee);
	}
	
	@Override
	public void deleteEmployee(Long id) {
	    Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
	
	// Helper Method (Entity → DTO)
	private EmployeeResponse mapToResponse(Employee employee) {
		
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
	    response.setName(employee.getName());
	    response.setEmail(employee.getEmail());
	    response.setDepartment(employee.getDepartment());
	    response.setSalary(employee.getSalary());
	    
	    return response;
	}
	


}

