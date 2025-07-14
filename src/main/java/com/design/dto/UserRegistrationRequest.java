package com.design.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationRequest {

	@NotNull
	@NotBlank
    private String name;

	@Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;
    
}
