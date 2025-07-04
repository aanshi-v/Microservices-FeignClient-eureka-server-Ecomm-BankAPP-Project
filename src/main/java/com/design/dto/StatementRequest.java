package com.design.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatementRequest {

	 @NotNull
	    private Long account;

	    @Min(1) @Max(12)
	    private int month;

	    @Min(2000)
	    private int year;
}
