package com.ck.ems.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {

	private LocalDateTime timestamp;
	private int status;
	private String message;
	private T data;
	
	 public ApiResponse(int status, String message, T data) {
	        this.timestamp = LocalDateTime.now();
	        this.status = status;
	        this.message = message;
	        this.data = data;
	    }

	
}
