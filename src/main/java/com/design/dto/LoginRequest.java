package com.design.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
	
	@Email
	 @NotNull
	 @NotBlank
	    private String email;

	    @NotNull
	    @NotBlank
	    private String password;

}
