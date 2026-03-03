package com.ck.ems.service;

import com.ck.ems.dto.EmployeeRequest;
import com.ck.ems.entity.Employee;
import com.ck.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {
	 	@Mock
	    private EmployeeRepository repository;

	    @InjectMocks
	    private EmployeeServiceImpl service;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testCreateEmployee() {

	        EmployeeRequest request = new EmployeeRequest();
	        request.setName("Chaitanya");
	        request.setEmail("chai@gmail.com");
	        request.setDepartment("IT");
	        request.setSalary(50000.0);

	        Employee savedEmployee = new Employee();
	        savedEmployee.setId(1L);
	        savedEmployee.setName("Chaitanya");

	        when(repository.save(any(Employee.class)))
	                .thenReturn(savedEmployee);

	        var response = service.createEmployee(request);

	        assertNotNull(response);
	        assertEquals("Chaitanya", response.getName());
	        verify(repository, times(1)).save(any(Employee.class));
	    }

}
