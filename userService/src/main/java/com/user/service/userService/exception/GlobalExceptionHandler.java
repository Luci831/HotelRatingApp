package com.user.service.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.userService.payload.ApiResponse;

@RestControllerAdvice
//handles exception throughout the project
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	//if this exception comes anywhere in project this is called
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		
		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
