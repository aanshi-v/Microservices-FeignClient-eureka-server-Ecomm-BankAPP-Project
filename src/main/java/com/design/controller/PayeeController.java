package com.design.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.PayeeDTO;
import com.design.entity.Payee;
import com.design.service.PayeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payee")
public class PayeeController {
	
	 @Autowired
	    private PayeeService payeeService;
	 
	 @Autowired
	    private ModelMapper modelMapper;

	    @PostMapping("/add")
	    public ResponseEntity<?> add(@Valid @RequestBody PayeeDTO dto) {
	        PayeeDTO saved = payeeService.addPayee(dto);
	        return ResponseEntity.ok(response("Success", "Payee added", 20000, saved));
	    }

	    @PutMapping("/update")
	    public ResponseEntity<?> update(@Valid @RequestBody PayeeDTO dto) {
	        PayeeDTO updated = payeeService.updatePayee(dto);
	        return ResponseEntity.ok(response("Success", "Payee updated", 20001, updated));
	    }
	    
	   
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable Long id) {
	        payeeService.deletePayee(id);
	        return ResponseEntity.ok(response("Success", "Payee deleted", 20002, null));
	    }

	    @GetMapping("/customer/{accountNumber}")
	    public ResponseEntity<?> getByCustomer(@PathVariable Long accountNumber) {
	        List<PayeeDTO> list = payeeService.getPayeesByCustomer(accountNumber);
	        return ResponseEntity.ok(response("Success", "Payees fetched", 20003, list));
	    }
	    
	    
//	    @PatchMapping("/patch/{id}")
//	    public ResponseEntity<?> patchPayee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//	        Payee payee = payeeService.patchPayee(id, updates);
//	        PayeeDTO dto = modelMapper.map(payee, PayeeDTO.class);
//	        return ResponseEntity.ok(response("Success", "Payee partially updated", 20004, dto));
//	    }
	    
	    @PatchMapping("/patch/{id}")
	    public ResponseEntity<?> patchPayee(@PathVariable Long id, @RequestBody PayeeDTO patchDto) {
	        Payee payee = payeeService.patchPayee(id, patchDto);
	        PayeeDTO dto = modelMapper.map(payee, PayeeDTO.class);
	        return ResponseEntity.ok(response("Success", "Payee partially updated", 20004, dto));
	    }
	    
	    

	    private Map<String, Object> response(String status, String message, int code, Object data) {
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("status", status);
	        map.put("message", message);
	        map.put("code", code);
	        map.put("data", data);
	        return map;
	    }

}
