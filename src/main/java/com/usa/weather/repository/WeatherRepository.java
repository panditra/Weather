package com.usa.weather.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.usa.weather.model.WeatherForecast;

@Repository
public class WeatherRepository {

	private final RestTemplate restTemplate;
	private final String foreCastURL;
	private final String apiKey;

	public WeatherRepository(@Value("${app.weather.api.key}") String apiKey,
			@Value("${app.weather.forecastURL}") String foreCastURL) {
		this.restTemplate = new RestTemplate();
		this.foreCastURL = foreCastURL;
		this.apiKey = apiKey;
	}

	public Optional<WeatherForecast> getWeatherForecast(String zipcode) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.foreCastURL)
				.queryParam("postal_code", zipcode).queryParam("key", this.apiKey).queryParam("country", "US")
				.queryParam("hours", "48");
		String uriString = builder.toUriString();
		return invoke(uriString, WeatherForecast.class);
	}

	private Optional<WeatherForecast> invoke(String url, Class<WeatherForecast> responseType) {
		RequestEntity<?> request = RequestEntity.get(url).build();
		ResponseEntity<WeatherForecast> exchange = this.restTemplate.exchange(request, responseType);
		return Optional.ofNullable(exchange.getBody());
	}
}
