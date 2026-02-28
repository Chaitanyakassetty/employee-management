package com.ck.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
	
	   private String name;
	   private String email;
	   private String department;
	   private Double salary;
}
