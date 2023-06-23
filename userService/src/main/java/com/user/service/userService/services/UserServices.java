package com.user.service.userService.services;

import java.util.List;

import com.user.service.userService.entities.User;



public interface UserServices {
	
	//user operations
	
	//create
	User saveUser(User user);
	
	//getAllUsers
	
	List<User> getAllUsers();
	
	//userById
	
	User getUser(String userId);
	
	
	
	
	
	

}
