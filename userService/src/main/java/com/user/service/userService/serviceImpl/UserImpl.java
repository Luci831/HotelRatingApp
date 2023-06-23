package com.user.service.userService.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.userService.entities.Hotel;
import com.user.service.userService.entities.Rating;
import com.user.service.userService.entities.User;
import com.user.service.userService.exception.ResourceNotFoundException;
import com.user.service.userService.external.HotelService;
import com.user.service.userService.repository.UserRepository;
import com.user.service.userService.services.UserServices;

@Service
public class UserImpl implements UserServices{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private HotelService hotelService;
	
	private Logger logger= LoggerFactory.getLogger(UserImpl.class);
	
	@Override
	public User saveUser(User user) {

		User savedUser = userRepo.save(user);
		return savedUser;
	}

	@Override
	public List<User> getAllUsers() {

		List<User> findAll = userRepo.findAll();
		return findAll;
	}

	@Override
	public User getUser(String userId) {

		//Getting user from db using userRepo
		 User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id"+userId));
		
		//get rating of the above user using RATING_SERVICE
			//http://localhost:8083/ratings/hotels/785ffe57-87d1-4abb-8778-df8fe947efe4
		 logger= LoggerFactory.getLogger(UserImpl.class);
		 
			Rating[] userRatings = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(),Rating[].class);
			logger.info("{}",userRatings);
			
			//get hotels from list of ratings
			
			List<Rating> list = Arrays.stream(userRatings).toList();
//			
			List<Rating> ratingList = list.stream().map(rating->{
				
				Hotel hotel= restTemplate.getForObject("http://localhost:8081/hotels/"+rating.getHotelId(), Hotel.class);
//				Hotel hotel= hotelService.getHotel(rating.getHotelId());
				
				rating.setHotel(hotel);
				
				return rating;
			}).collect(Collectors.toList());
			
		user.setRatings(ratingList);
			
		return user;
	}

}
