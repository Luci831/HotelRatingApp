package com.Rating.RatingService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.WebFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		
		http.authorizeHttpRequests()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .oauth2ResourceServer()
		    .jwt();
		
		return http.build();
	}

}
