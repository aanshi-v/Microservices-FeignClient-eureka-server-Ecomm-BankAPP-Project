package com.design.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private int age;
	    private String gender;
	    
	    @NotNull
	    @Column(unique = true)
	    private String email;
	    private String phone;
	    @NotNull
	    private Long accountNumber;
	    private double balance;

}
