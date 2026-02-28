package com.ck.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ck.ems.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	 // Spring Data JPA will automatically implement this
    boolean existsByEmail(String email);
    
 //  Derived query method
    Page<Employee> findByDepartment(String department, Pageable pageable);

}