package com.design.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.BuyRequest;
import com.design.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	  @Autowired
	    private OrderService orderService;

	    @PostMapping("/buy")
	    public ResponseEntity<?> buy(@RequestBody BuyRequest request) {
	    	 Map<String, Object> response = orderService.placeOrder(request);
	         return ResponseEntity.ok(response);
	     }
	    

}
