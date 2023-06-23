package com.ApiGateway.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig   {
	
//	public ReactiveJwtDecoder reactiveJwtDecoder() {
//        // Return an instance of ReactiveJwtDecoder implementation
//        // or configure and create an instance according to your requirements
//		
//		return new ReactiveJwtDecoder() {
//			
//			@Override
//			public Mono<Jwt> decode(String token) throws JwtException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//    }

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity)
	{
		httpSecurity
		  .authorizeExchange()
		  .anyExchange()
		  .authenticated()
		  .and()
		  .oauth2Client()
		  .and()
		  .oauth2ResourceServer()
		  .jwt();
		
		return httpSecurity.build();
		  
		  
		  
		  
		  
	}
}
