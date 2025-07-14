package com.design.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.design.dto.ApiResponse;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {
	        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), 400, null);
	        return ResponseEntity.badRequest().body(response);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception ex) {
	        ApiResponse<Object> response = new ApiResponse<>("Something went wrong", 500, null);
	        return ResponseEntity.status(500).body(response);
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );

	        ApiResponse<Object> response = new ApiResponse<>(
	                "Validation failed",
	                400,
	                errors
	        );
	        return ResponseEntity.badRequest().body(response);
	    }


//	 @ExceptionHandler(RuntimeException.class)
//	    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
//	        Map<String, Object> error = new LinkedHashMap<>();
//	        error.put("status", "FAIL");
//	        error.put("message", ex.getMessage());
//	        error.put("code", 400);
//	        error.put("data", null);
//	        return ResponseEntity.badRequest().body(error);
//	    }
	
	
	
	@ExceptionHandler(CustomerValidationException.class)
    public ResponseEntity<?> handleCustomerValidationException(CustomerValidationException ex) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "FAIL");
        res.put("message", ex.getMessage());
        res.put("code", 400);
        res.put("data", null);
        return ResponseEntity.badRequest().body(res);
    }

	
	
	 @ExceptionHandler(FeignException.class)
	    public ResponseEntity<?> handleFeignException(FeignException ex) {
	        Map<String, Object> error = new HashMap<>();
	        error.put("status", "FAIL");
	        error.put("message", "Customer ID or Account Number is invalid");
	        error.put("code", 400);
	        error.put("data", null);
	        return ResponseEntity.badRequest().body(error);
	    }
	
	
	
	
}
