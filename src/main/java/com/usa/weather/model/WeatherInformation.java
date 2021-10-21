package com.usa.weather.model;

public class WeatherInformation {
	private String time;
	private String temperature;

	public WeatherInformation(String time, String string) {
		this.setTime(time);
		this.setTemperature(string);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
