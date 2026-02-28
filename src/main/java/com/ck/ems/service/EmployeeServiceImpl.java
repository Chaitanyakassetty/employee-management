package com.ck.ems.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ck.ems.dto.EmployeeRequest;
import com.ck.ems.dto.EmployeeResponse;
import com.ck.ems.entity.Employee;
import com.ck.ems.exception.EmailAlreadyExistsException;
import com.ck.ems.exception.ResourceNotFoundException;
import com.ck.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
	   
		logger.info("Creating employee with email: {}", employeeRequest.getEmail());
		
		 //  Check before saving
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists: " + employeeRequest.getEmail());
        }
		
		// Convert DTO → Entity
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());
        
        // Convert Entity → Response DTO
        return mapToResponse(savedEmployee);
    }

	// GET BY ID
	@Override
	public EmployeeResponse  getEmployeeById(Long id) {
		
		logger.info("Fetching employee with ID: {}", id);
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> { 
					logger.error("Employee not found with ID: {}", id);
					return new ResourceNotFoundException("Employee not found" + id);
		});
				
		return mapToResponse(employee);
	}


	// GET ALL
//	@Override
//	public List<EmployeeResponse> getAllEmployees() {
//	     List<Employee> employees = employeeRepository.findAll();
//
//	        return employees.stream()
//	                .map(this::mapToResponse)
//	                .collect(Collectors.toList());
//	    }

	@Override
	public Page<EmployeeResponse> getAllEmployees(
			int page, int size, String sortBy, String direction) {
		
		Sort sort = direction.equalsIgnoreCase("asc") ?
				Sort.by(sortBy).ascending():
				Sort.by(sortBy).descending();
				
		Pageable pageable = PageRequest.of(page, size, sort);
		
		Page<Employee> employeePage = employeeRepository.findAll(pageable);
		
		return employeePage.map(this::mapToResponse);
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
	
	@Override
	public Page<EmployeeResponse> getEmployeesByDepartment(
	        String department, int page, int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Employee> employeePage =
	            employeeRepository.findByDepartment(department, pageable);

	    return employeePage.map(this::mapToResponse);
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