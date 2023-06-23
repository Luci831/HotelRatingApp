package com.hotel.services.HotelService.service;

import java.util.List;

import com.hotel.services.HotelService.entities.Hotel;

public interface HotelService {

	//create
	
	Hotel createHotel(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//getById
	
	Hotel getById(String id);
	
	
}
