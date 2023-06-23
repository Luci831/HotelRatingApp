package com.Rating.RatingService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user_ratings")
public class Ratings {

	@Id
	//ids are auto generated in mongodb
	private String ratingId;

	private String userId;

	private String hotelId;

	private int rating;

	private String feedback;
}
