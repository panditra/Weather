package com.usa.weather.service;

import java.util.Date;

import com.usa.weather.model.WeatherInformation;

public interface WeatherService {
	public WeatherInformation getHourlyTemperatureDataForTomorrow(String zipCode, Date nextDay);

}
