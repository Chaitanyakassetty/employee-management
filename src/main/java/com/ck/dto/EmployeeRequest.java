package com.ck.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequest {
	
	   @NotBlank(message = "Name is required")
	   private String name;
	   
	   @NotBlank(message = "Email is required")
	   @Email(message = "Email format is invalid")
	   private String email;
	   
	   @NotBlank(message = "Department is required")
	   private String department;
	   
	   @NotNull(message = "Salary is required")
	   @Positive(message = "Salary must be greater than 0")
	   private Double salary;
}
