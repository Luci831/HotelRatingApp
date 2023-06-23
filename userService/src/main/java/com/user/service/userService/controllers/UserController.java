package com.user.service.userService.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.service.userService.entities.User;
import com.user.service.userService.serviceImpl.UserImpl;
import com.user.service.userService.services.UserServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices userService;
	
	private Logger logger=LoggerFactory.getLogger(UserImpl.class);

	// create

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

		User saveUser = userService.saveUser(user);

		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService.getAllUsers();

		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	int retry=1;
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallBack")
//	@Retry(name="ratingHotelService" ,fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter" ,fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		// get user from db using user repository
		User user = userService.getUser(userId);

		logger.info("Retry Count: "+retry);
		retry++;
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	// creating fallBack method for circuitBreaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {

		logger.info("The fallback is executed beacause service is down: ",ex.getMessage());
		
		User user = new User();
		user.setEmail("dummy@gmail.com");
		user.setAbout("dummy user");
		user.setName("dummy");
		user.setUserId("12345");
		
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
