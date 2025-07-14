package com.design.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PayeeDTO {

	private Long id;

    @NotNull
    private Long customerAccountNumber;

    @NotBlank
    private String payeeName;

    @NotBlank
    private String payeeAccountNumber;

    @NotBlank
    private String bankName;
}
