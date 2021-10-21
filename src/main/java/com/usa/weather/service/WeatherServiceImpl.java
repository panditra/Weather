package com.usa.weather.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.weather.exception.InvalidZipCodeException;
import com.usa.weather.model.Weather;
import com.usa.weather.model.WeatherForecast;
import com.usa.weather.model.WeatherInformation;
import com.usa.weather.repository.WeatherRepository;
import com.usa.weather.util.DateUtil;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	DateUtil dateUtil;

	@Override
	public WeatherInformation getHourlyTemperatureDataForTomorrow(String zipCode, Date nextDay) {
		Optional<WeatherForecast> weatherForecast = weatherRepository.getWeatherForecast(zipCode);
		if (weatherForecast.isPresent()) {
			List<Weather> filterWeatherList = weatherForecast.get().getData().stream()
					.filter(weather -> dateUtil.compareTwoDates(weather.getTimestamp(), nextDay))
					.collect(Collectors.toList());
			filterWeatherList.sort((Weather w1, Weather w2) -> Double.compare(Double.parseDouble(w1.getTemp()),
					Double.parseDouble(w2.getTemp())));
			return new WeatherInformation(dateUtil.dateFormatter(filterWeatherList.get(0).getTimestamp()),
					filterWeatherList.get(0).getTemp());

		}else{
            throw(new InvalidZipCodeException());
        }

	}

}
