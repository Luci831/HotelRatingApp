package com.hotel.services.HotelService.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.services.HotelService.entities.Hotel;
import com.hotel.services.HotelService.exception.ResourceNotFoundException;
import com.hotel.services.HotelService.repository.HotelRepository;
import com.hotel.services.HotelService.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		
		String hotelId = UUID.randomUUID().toString();
		
		hotel.setId(hotelId);
		Hotel hotel1 = hotelRepo.save(hotel);
		
		return hotel1;
	}

	@Override
	public List<Hotel> getAll() {

		List<Hotel> findAll = hotelRepo.findAll();
		
		return findAll;
	}

	@Override
	public Hotel getById(String id) {

		Hotel hotel = hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with given id"));
		return hotel;
	}

}
