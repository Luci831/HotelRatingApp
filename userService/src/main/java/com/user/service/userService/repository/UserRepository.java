package com.user.service.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.userService.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
