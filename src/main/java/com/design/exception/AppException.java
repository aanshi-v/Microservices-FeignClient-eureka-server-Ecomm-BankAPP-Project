package com.design.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//--- Custom Exception ---
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppException extends RuntimeException{

	 public AppException(String message) {
	        super(message);
	    }
}
