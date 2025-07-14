package com.design.dto;

import lombok.Data;

@Data
public class TransferRequest {
	
	 private Long fromAccount;
	    private Long toAccount;
	    private double amount;

}
