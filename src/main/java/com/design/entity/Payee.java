package com.design.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "payee", uniqueConstraints = @UniqueConstraint(columnNames = "payee_account_number"))
public class Payee {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long customerAccountNumber;
	    private String payeeName;
	    
	    @Column(name = "payee_account_number")
	    private String payeeAccountNumber;
	    
	    private String bankName;

}
