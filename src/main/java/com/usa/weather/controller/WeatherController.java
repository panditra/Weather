package com.usa.weather.controller;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.usa.weather.model.WeatherInformation;
import com.usa.weather.service.WeatherService;
import com.usa.weather.util.DateUtil;
import com.usa.weather.validation.constraint.ValidateZipCode;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DateUtil dateUtil;

	@ApiOperation(value = "View a hourly temperature for tomorrow", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "No data found for given postal code"),
			@ApiResponse(code = 400, message = "Incorrect postal code"),
			@ApiResponse(code = 500, message = "Something went wrong") })
	@GetMapping("/api/weather/{zipcode}")
	public WeatherInformation getWeatherForecast(@ValidateZipCode @PathVariable String zipcode) {
		return weatherService.getHourlyTemperatureDataForTomorrow(zipcode, dateUtil.getTomorrowDate(DateTime.now()));
	}

}
