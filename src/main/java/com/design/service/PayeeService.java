package com.design.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dto.PayeeDTO;
import com.design.entity.Payee;
import com.design.repository.PayeeRepository;

@Service
public class PayeeService {

	 @Autowired
	    private PayeeRepository payeeRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    public PayeeDTO addPayee(PayeeDTO dto) {
	        List<Payee> existing = payeeRepository.findByCustomerAccountNumber(dto.getCustomerAccountNumber());
	        if (existing.size() >= 5) {
	            throw new RuntimeException("Maximum 5 payees allowed.");
	        }
	        Payee payee = modelMapper.map(dto, Payee.class);
	        return modelMapper.map(payeeRepository.save(payee), PayeeDTO.class);
	    }

	    
	    public PayeeDTO updatePayee(PayeeDTO dto) {
	        Payee payee = payeeRepository.findById(dto.getId())
	                .orElseThrow(() -> new RuntimeException("Payee not found"));
	        modelMapper.map(dto, payee);
	        return modelMapper.map(payeeRepository.save(payee), PayeeDTO.class);
	    }
	    
	    
	    
	    public void deletePayee(Long id) {
	        if (!payeeRepository.existsById(id)) {
	            throw new RuntimeException("Payee not found");
	        }
	        payeeRepository.deleteById(id);
	    }

	    
	    public List<PayeeDTO> getPayeesByCustomer(Long customerAccount) {
	        return payeeRepository.findByCustomerAccountNumber(customerAccount)
	                .stream()
	                .map(p -> modelMapper.map(p, PayeeDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    
	    
//	    public Payee patchPayee(Long id, Map<String, Object> updates) {
//	        Payee payee = payeeRepository.findById(id)
//	                .orElseThrow(() -> new RuntimeException("Payee not found"));
//
//	        updates.forEach((key, value) -> {
//	            switch (key) {
//	                case "payeeName":
//	                    payee.setPayeeName(value.toString());
//	                    break;
//	                case "payeeAccountNumber":
//	                    payee.setPayeeAccountNumber(value.toString());
//	                    break;
//	                case "bankName":
//	                    payee.setBankName(value.toString());
//	                    break;
//	                default:
//	                    throw new RuntimeException("Invalid field: " + key);
//	            }
//	        });
//
//	        return payeeRepository.save(payee);
//	    }
	    
	    
	    public Payee patchPayee(Long id, PayeeDTO patchDto) {
	        Payee existing = payeeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Payee not found"));

	        if (patchDto.getPayeeName() != null) {
	            existing.setPayeeName(patchDto.getPayeeName());
	        }
	        if (patchDto.getPayeeAccountNumber() != null) {
	            existing.setPayeeAccountNumber(patchDto.getPayeeAccountNumber());
	        }
	        if (patchDto.getBankName() != null) {
	            existing.setBankName(patchDto.getBankName());
	        }

	        return payeeRepository.save(existing);
	    }
	    
	    
	    
	    
}














