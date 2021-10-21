package com.usa.weather.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherForecast {

	private List<Weather> data = new ArrayList<>();

	@JsonProperty("data")
	public List<Weather> getData() {
		return this.data;
	}

	@JsonSetter("data")
	public void setData(List<Weather> data) {
		this.data = data;
	}

}
