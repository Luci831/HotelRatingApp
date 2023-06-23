package com.hotel.services.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.services.HotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
