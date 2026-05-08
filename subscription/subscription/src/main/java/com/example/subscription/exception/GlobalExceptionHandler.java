package com.example.subscription.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.subscription.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleResourceNotFound(
			ResourceNotFoundException ex){
		
		ApiResponse<String> response = new ApiResponse<String>(
				false,
				ex.getMessage(),null
				);
		
		return new ResponseEntity<ApiResponse<String>>(
				response,HttpStatus.NOT_FOUND
				);
				
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>>
	handleValidationExceptions(
			MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult()
		.getFieldErrors().forEach(error ->
		errors.put(
				error.getField(),
				error.getDefaultMessage()
				)
		);
		
		ApiResponse<Map<String, String>> response = 
				new ApiResponse<Map<String,String>>(
						false,"validation Failed",errors);
		
		return new ResponseEntity<ApiResponse<Map<String,String>>>(
				response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex){
		
		ApiResponse<String> response = new ApiResponse<String>(
				false,ex.getMessage(),null);
		return new ResponseEntity<ApiResponse<String>>(
				response,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
