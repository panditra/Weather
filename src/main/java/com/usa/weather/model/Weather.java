package com.usa.weather.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Weather {

	private Date timestamp;

	private String temp;

	public Weather() {
	}

	public Weather(Date timestamp, String temp) {
		this.timestamp = timestamp;
		this.temp = temp;
	}

	@JsonProperty("timestamp_local")
	public Date getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("timestamp_local")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setTimestamp(Date localTime) {
		this.timestamp = localTime;
	}

	public String getTemp() {
		return this.temp;
	}

	public void setTemp(String temperature) {
		this.temp = temperature;
	}

	@JsonProperty("data")
	public void setData(List<Map<String, String>> data) {
		for (Map<String, String> dataValue : data)
			setTemp(dataValue.get("temp"));
	}

}
