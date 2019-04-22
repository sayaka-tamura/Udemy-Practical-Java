package com.course.practicaljava.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.practicaljava.exception.IllegalApiParamException;
import com.course.practicaljava.rest.domain.ErrorResponse;

@RestControllerAdvice // Annotation for Global Exception Handler
public class GlobalRestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@ExceptionHandler(IllegalApiParamException.class) // Annotation to handle an exception
	public ResponseEntity<ErrorResponse> handleIllegalApiParamException(IllegalApiParamException e) {
		var errorMessage = "Exception IllegalApiParamException from GlobalRestExceptionHandler : " + e.getMessage();
		// error message for console
		log.warn(errorMessage);

		var errorResponse = new ErrorResponse(errorMessage, System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
	}

}
