package com.design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.design.dto.ProductRequest;
import com.design.entity.Product;
import com.design.repository.ProductRepository;

@Service
public class ProductService {
	
	 @Autowired
	    private ProductRepository productRepository;
	 

//	    public Page<Product> searchProducts(String name, int page) {
//	        Pageable pageable = PageRequest.of(page, 5);
//	        return productRepository.findByNameContainingIgnoreCase(name, pageable);
//	    }
	 
	 public List<Product> searchProducts(String name, int page) {
		    Pageable pageable = PageRequest.of(page, 5);
		    Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(name, pageable);
		    return productPage.getContent(); // ✅ Return only the list of products
		}

	    
	    
	    
	    public Product saveProduct(ProductRequest request) {
	        Product product = new Product();
	        product.setName(request.getName());
	        product.setPrice(request.getPrice());
	        product.setQuantity(request.getQuantity()); 
	        return productRepository.save(product);
	    }

}
