package com.Rating.RatingService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rating.RatingService.entities.Ratings;
import com.Rating.RatingService.repository.RatingRepository;
import com.Rating.RatingService.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	@Override
	public Ratings createRating(Ratings rating) {
		
		Ratings savedRating = ratingRepo.save(rating);
		return savedRating;
	}

	@Override
	public List<Ratings> getAllRatings() {
		
		List<Ratings> allRatings = ratingRepo.findAll();
		return allRatings;
	}

	@Override
	public List<Ratings> getByUserId(String userId) {
		
		
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Ratings> getByHotelId(String hotelId) {

		return ratingRepo.findByHotelId(hotelId);
	}

}
