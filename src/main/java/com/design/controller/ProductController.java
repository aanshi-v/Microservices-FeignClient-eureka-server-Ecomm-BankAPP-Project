package com.design.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.ProductRequest;
import com.design.entity.Product;
import com.design.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;

	
//    @GetMapping("/search")
//    public ResponseEntity<Page<Product>> search(
//        @RequestParam String name,
//        @RequestParam(defaultValue = "0") int page
//    ) {
//        return ResponseEntity.ok(productService.searchProducts(name, page));
//    }
	
	 @GetMapping("/search")
	    public ResponseEntity<List<Product>> search( 
	        @RequestParam String name,
	        @RequestParam(defaultValue = "0") int page
	    ) {
	        return ResponseEntity.ok(productService.searchProducts(name, page));
	    }
    
    
    
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductRequest request) {
        Product savedProduct = productService.saveProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    
    

    
}
