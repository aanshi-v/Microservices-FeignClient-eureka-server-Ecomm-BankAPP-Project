package com.design.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FundTransferRequest {

	@NotNull
    private Long fromAccount;

    @NotNull
    private Long toAccount;

    @Positive
    private double amount;
}
