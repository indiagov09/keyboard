package com.test.utils;
import java.util.*;
import java.util.stream.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.function.*;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<CustomResponse> nosuchelementexceptionhandler(NoSuchElementException ex){
		String message = ex.getLocalizedMessage();
		return new ResponseEntity<CustomResponse>(new CustomResponse(message),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomResponse> IllegalArgumentxceptionhandler(IllegalArgumentException ex){
		String message = ex.getLocalizedMessage();
		return new ResponseEntity<CustomResponse>(new CustomResponse(message),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<CustomResponse> NoResourceFoundExceptionhandler(NoResourceFoundException ex){
		String message = ex.getLocalizedMessage();
		return new ResponseEntity<CustomResponse>(new CustomResponse(message),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<CustomResponse> NullPointerExceptionExceptionhandler(NullPointerException ex){
		String message = ex.getLocalizedMessage();
		return new ResponseEntity<CustomResponse>(new CustomResponse(message),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
//