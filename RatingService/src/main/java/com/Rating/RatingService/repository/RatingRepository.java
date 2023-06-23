package com.Rating.RatingService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Rating.RatingService.entities.Ratings;

public interface RatingRepository extends MongoRepository<Ratings, String>{
	
	//custom finder methods
	List<Ratings> findByUserId(String userId);
	
	List<Ratings> findByHotelId(String hotelId);
	

}
