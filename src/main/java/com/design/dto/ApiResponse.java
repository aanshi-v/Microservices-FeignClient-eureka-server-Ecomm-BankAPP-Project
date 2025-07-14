package com.design.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	private String message;
    private int code;
    private T data;

    public ApiResponse(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

}
