package com.Rating.RatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rating.RatingService.entities.Ratings;
import com.Rating.RatingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<Ratings> createRatings(@RequestBody Ratings ratings)
	{
		Ratings createRating = ratingService.createRating(ratings);
		
		return new ResponseEntity<Ratings>(createRating,HttpStatus.CREATED);
		
		
	}
	@GetMapping("/")
	public ResponseEntity<List<Ratings>> getAllRatings()
	{
		List<Ratings> allRatings = ratingService.getAllRatings();
		
		return new ResponseEntity<List<Ratings>>(allRatings,HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Ratings>> getRatingByUserId(@PathVariable String userId)
	{
		List<Ratings> ratings = ratingService.getByUserId(userId);
		
		return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Ratings>> getRatingByHotelId(@PathVariable String hotelId)
	{
		List<Ratings> ratings = ratingService.getByHotelId(hotelId);
		
		return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.OK);
	}

}
