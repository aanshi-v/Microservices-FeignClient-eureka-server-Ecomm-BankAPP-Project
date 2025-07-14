package com.design.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.design.dto.BuyItem;
import com.design.dto.BuyRequest;
import com.design.dto.TransferRequest;
import com.design.entity.OrderEntity;
import com.design.entity.Product;
import com.design.exception.CustomerValidationException;
import com.design.feign.BankClient;
import com.design.repository.OrderRepository;
import com.design.repository.ProductRepository;



@Service
public class OrderService {
	
	 @Autowired
	    private BankClient bankClient;

	    @Autowired
	    private OrderRepository orderRepo;
	    
	    @Autowired
	    private ProductRepository productRepo;

	      
	    
	     
	    private final Long companyAccountNumber = 8018758400L;

	    public Map<String, Object> placeOrder(BuyRequest request) {
//Step 1: Customer Validation // Validate customer using Feign 
	    	
	        ResponseEntity<Map<String, Object>> validationResp = bankClient.validateCustomer(
	            request.getCustomerId(),
	            request.getAccountNumber()
	        );

	        if (!validationResp.getStatusCode().is2xxSuccessful()) {
	            throw new CustomerValidationException("Customer ID or Account Number is invalid");
	        }

// Step 2: Product Validation & Order Preparation  // Process Order	        
	       
	        List<OrderEntity> orderEntities = new ArrayList<>();
	        double total = 0;

	        for (BuyItem item : request.getContent()) {
	            Product product = productRepo.findById(item.getProductId()).orElseThrow(() ->
	                new RuntimeException("Product with ID " + item.getProductId() + " not found"));

	            if (product.getQuantity() < item.getQuantity()) {
	                throw new RuntimeException("Insufficient quantity for product: " + product.getName());
	            }

	            product.setQuantity(product.getQuantity() - item.getQuantity());
	            productRepo.save(product);

	            OrderEntity order = new OrderEntity();
	            order.setCustomerId(request.getCustomerId());
	            order.setAccountNumber(request.getAccountNumber());
	            order.setProductId(product.getId());
	            order.setProductName(product.getName());
	            order.setPrice(product.getPrice());
	            order.setQuantity(item.getQuantity());

	            double itemTotal = product.getPrice() * item.getQuantity();
	            total += itemTotal;
	            order.setTotalAmount(itemTotal);
	            order.setCreatedAt(LocalDateTime.now());
	            order.setStatus("PENDING");
	            orderEntities.add(order);
	        }

//Step 3: Bank Transfer // Transfer money
	        TransferRequest transferRequest = new TransferRequest();
	        transferRequest.setFromAccount(request.getAccountNumber());
	        transferRequest.setToAccount(companyAccountNumber);
	        transferRequest.setAmount(total);

	        ResponseEntity<Map<String, Object>> response = bankClient.transfer(transferRequest);

	        if (!response.getStatusCode().is2xxSuccessful()) {
	            throw new RuntimeException("Bank transfer failed: " + response.getBody().get("message"));
	        }

//Step 4: Save Orders & Return Response	        
	        String status = "SUCCESS";
	        List<Map<String, Object>> orderData = new ArrayList<>();
	        for (OrderEntity entity : orderEntities) {
	            entity.setStatus(status);
	            OrderEntity saved = orderRepo.save(entity);

	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("id", saved.getId());
	            map.put("customerId", saved.getCustomerId());
	            map.put("accountNumber", saved.getAccountNumber());
	            map.put("productId", saved.getProductId());
	            map.put("productName", saved.getProductName());
	            map.put("price", saved.getPrice());
	            map.put("quantity", saved.getQuantity());
	            map.put("totalAmount", saved.getTotalAmount());
	            map.put("status", saved.getStatus());
	            map.put("createdAt", saved.getCreatedAt());
	            orderData.add(map);
	        }

	        Map<String, Object> resp = new HashMap<>();
	        resp.put("status", "SUCCESS");
	        resp.put("message", "Order placed successfully");
	        resp.put("code", 10003);
	        resp.put("data", orderData);
	        return resp;
	    }   
	    
	    
	    
	    
	    
	    
	    
	   	        
	    
	    
	    
	    
}
