package com.ecom.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(EmailAlreadyExistsException.class)
	    public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
	        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(field -> {
	            errors.put(field.getField(), field.getDefaultMessage());
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleGenericException(Exception ex) {
	        return buildErrorResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    private ResponseEntity<?> buildErrorResponse(String message, HttpStatus status) {
	        Map<String, Object> error = new HashMap<>();
	        error.put("timestamp", Instant.now());
	        error.put("status", status.value());
	        error.put("error", status.getReasonPhrase());
	        error.put("message", message);
	        return new ResponseEntity<>(error, status);
	    }

}
