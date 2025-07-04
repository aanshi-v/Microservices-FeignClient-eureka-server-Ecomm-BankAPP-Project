package com.design.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
	        Map<String, Object> res = new HashMap<>();
	        res.put("status", "Fail");
	        res.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());
	        res.put("code", 400);
	        res.put("data", null);
	        return ResponseEntity.badRequest().body(res);
	    }

//	    @ExceptionHandler(RuntimeException.class)
//	    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
//	        Map<String, Object> res = new HashMap<>();
//	        res.put("status", "Error");
//	        res.put("message", ex.getMessage());
//	        res.put("code", 500);
//	        res.put("data", null);
//	        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
	    
	    
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
	        Map<String, Object> error = new LinkedHashMap<>();
	        error.put("status", "FAIL");
	        error.put("message", ex.getMessage());
	        error.put("code", 400);
	        error.put("data", null);
	        return ResponseEntity.badRequest().body(error);
	    }

	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
	        String message = "Duplicate entry or data integrity violation";

	        if (ex.getMessage() != null && ex.getMessage().contains("email")) {
	            message = "Email already exists";
	        }

	        Map<String, Object> res = new HashMap<>();
	        res.put("status", "FAIL");
	        res.put("message", message);
	        res.put("code", 400);
	        res.put("data", null);
	        return ResponseEntity.badRequest().body(res);
	    }
	
	
	
	

    
}
