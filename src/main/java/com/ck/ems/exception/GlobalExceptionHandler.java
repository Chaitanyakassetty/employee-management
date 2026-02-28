package com.ck.ems.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(org.springframework.web.bind.MethodArgumentNotValidException ex, HttpServletRequest request){
		
		Map<String, String> errors = new HashMap<String,String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()) 
				);
		
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailExists(
	        EmailAlreadyExistsException ex,
	        HttpServletRequest request) {

	    ErrorResponse error = new ErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            "Bad Request",
	            ex.getMessage(),
	            request.getRequestURI()
	    );

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
