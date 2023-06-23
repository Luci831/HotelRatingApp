package com.hotel.services.HotelService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.services.HotelService.entities.Hotel;
import com.hotel.services.HotelService.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//create
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel createHotel = hotelService.createHotel(hotel);
		
		return new ResponseEntity<Hotel>(createHotel,HttpStatus.CREATED);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		List<Hotel> hotels = hotelService.getAll();
		
		return new ResponseEntity<List<Hotel>>(hotels,HttpStatus.OK);
	}
	
	//getById
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId)
	{
		Hotel hotel = hotelService.getById(hotelId);
		
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
}
