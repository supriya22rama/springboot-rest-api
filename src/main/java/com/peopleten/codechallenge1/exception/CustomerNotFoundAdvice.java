package com.peopleten.codechallenge1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.peopleten.codechallenge1.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerNotFoundAdvice extends ResponseEntityExceptionHandler{

	@ResponseBody
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String customerNotFoundException(CustomerNotFoundException e) {
		return e.getMessage();
	}

}
