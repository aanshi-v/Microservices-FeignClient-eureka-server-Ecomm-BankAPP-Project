package com.design.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationRequest {
	
	@NotBlank
    private String name;

    @Min(18)
    private int age;

    @NotBlank
    private String gender;

    @Email
    private String email;

    @NotBlank
    private String phone;

}
