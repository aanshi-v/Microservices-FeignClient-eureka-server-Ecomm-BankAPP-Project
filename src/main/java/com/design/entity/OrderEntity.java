package com.design.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long customerId;
	    private Long accountNumber;

//	    @Lob
//	    private String itemsJson;

	    private Double totalAmount;
	    private String status;
	    private LocalDateTime createdAt;
	    
	    
	    private Long productId;
	    private String productName;
	    private Double price;
	    private int quantity;
}
