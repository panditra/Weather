package com.usa.weather.exception.handler;

import com.usa.weather.exception.ErrorResponse;
import com.usa.weather.exception.InvalidZipCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidZipCodeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidZipCodeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPostalCode(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setError("No data found for given postal code.");
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}
}
