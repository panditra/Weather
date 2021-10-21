package com.usa.weather.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usa.weather.exception.ErrorResponse;

@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setError("You have entered invalid zip code.");
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}
