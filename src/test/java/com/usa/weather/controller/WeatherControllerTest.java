package com.usa.weather.controller;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.usa.weather.exception.InvalidZipCodeException;
import com.usa.weather.model.WeatherInformation;
import com.usa.weather.service.WeatherService;
import com.usa.weather.util.DateUtil;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = WeatherController.class)
class WeatherControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@InjectMocks
	private WeatherController weatherController;
	
	@MockBean
	DateUtil dateUtil;

	@Test
	void getColdestWeather_ValidZipCode() throws Exception {
		WeatherInformation weatherInformation = new WeatherInformation("", Float. toString(-5F));
		when(weatherService.getHourlyTemperatureDataForTomorrow("12345", dateUtil.getTomorrowDate(DateTime.now())))
				.thenReturn(weatherInformation);

		Assert.assertEquals(weatherInformation, weatherController.getWeatherForecast("12345"));

		mockMvc.perform(get("/api/weather/{zipcode}", "12345")).andExpect(status().isOk());
	}

	@Test
	void getColdestWeather_InvalidZipCode() throws Exception {
		when(weatherService.getHourlyTemperatureDataForTomorrow("12345", dateUtil.getTomorrowDate(DateTime.now())))
				.thenThrow(new InvalidZipCodeException());
		Assertions.assertThrows(InvalidZipCodeException.class, () -> weatherController.getWeatherForecast("12345"));
		mockMvc.perform(get("/api/weather/{zipcode}", "12345"))
				.andExpect(status().isNotFound());
	}

	@Test
	void getColdestWeather_incorrectZipCode() throws Exception {
		mockMvc.perform(get("/api/weather/{zipcode}", "123"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void getColdestWeather_NoZipCode() throws Exception {
		mockMvc.perform(get("/api/weather/{zipcode}", "")).andExpect(status().isNotFound());
	}
}
