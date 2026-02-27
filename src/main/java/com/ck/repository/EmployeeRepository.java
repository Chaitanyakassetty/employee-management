package com.ck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ck.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}