package com.user.service.userService.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.user.service.userService.config.interceptors.RestTemplateInterceptor;

@Configuration
public class MyConfig {

	@Autowired
	private ClientRegistrationRepository client;
	
	@Autowired
	private OAuth2AuthorizedClientRepository oauthclient;
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate = new RestTemplate();
		
		List<ClientHttpRequestInterceptor> list=new ArrayList<>();
		
		list.add(new RestTemplateInterceptor(manager(client,oauthclient)));
		
		restTemplate.setInterceptors(list);
		
		return restTemplate;
	}
	
	//declare the bean of OAuth2AuthorizedClientManager
	
	@Bean
	public OAuth2AuthorizedClientManager manager(
			ClientRegistrationRepository client, OAuth2AuthorizedClientRepository oauthclient )
	{
		OAuth2AuthorizedClientProvider provider= OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
				
			
			
		
		
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = 
				new DefaultOAuth2AuthorizedClientManager(client, oauthclient);
		
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		
		return defaultOAuth2AuthorizedClientManager;
	}
}
