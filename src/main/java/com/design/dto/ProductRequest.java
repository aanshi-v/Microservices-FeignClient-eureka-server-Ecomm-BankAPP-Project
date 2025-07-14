package com.design.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

	 @NotNull
	    private String name;

	    @NotNull
	    private Double price;
	    
	    @NotNull
	    private Integer quantity;
}
