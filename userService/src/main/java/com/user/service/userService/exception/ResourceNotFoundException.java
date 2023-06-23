package com.user.service.userService.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException()
	{
		super("Resource not found in server !!!");
	}
	public ResourceNotFoundException(String str)
	{
		super(str);
	}
}
