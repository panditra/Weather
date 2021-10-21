package com.usa.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient {
	//comment
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
