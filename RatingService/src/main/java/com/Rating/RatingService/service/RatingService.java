package com.Rating.RatingService.service;

import com.Rating.RatingService.entities.Ratings;
import java.util.*;

public interface RatingService {
	
	//create
	Ratings createRating(Ratings rating);
	
	//getAll ratings
	List<Ratings> getAllRatings();
	
	
	//getAll ratings by userID
	List<Ratings> getByUserId(String userId);
	
	
	//getAll ratings by hotels
	List<Ratings> getByHotelId(String hotelId);

}
