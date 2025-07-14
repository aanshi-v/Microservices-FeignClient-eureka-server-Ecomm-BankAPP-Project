package com.design.dto;

import java.util.List;

import lombok.Data;

@Data
public class BuyRequest {
	
	  private Long customerId;
	    private Long accountNumber;
	    private List<BuyItem> content;

}
