package com.design.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dto.ApiResponse;
import com.design.dto.LoginRequest;
import com.design.dto.UserRegistrationRequest;
import com.design.dto.UserResponseDTO;
import com.design.entity.User;
import com.design.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
    private UserRepository userRepository;
	
	 @Autowired
	    private ModelMapper modelMapper;

	
//    public String register(UserRegistrationRequest request) {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            return "User already exists with email: " + request.getEmail();
//        }
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        userRepository.save(user);
//        return "User registered successfully";
//    }
	 
	 public ApiResponse<UserResponseDTO> register(UserRegistrationRequest request) {
		    if (userRepository.existsByEmail(request.getEmail())) {
		        throw new RuntimeException("User already exists with email: " + request.getEmail());
		    }

		    User user = modelMapper.map(request, User.class);
		    userRepository.save(user);

		    // Map saved user to response DTO
		    UserResponseDTO responseDTO = modelMapper.map(user, UserResponseDTO.class);

		    return new ApiResponse<>("User registered successfully", 200, responseDTO);
		}


    
    
//    public String login(LoginRequest request) {
//        Optional<User> user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
//        return user.isPresent() ? "Login successful" : "Invalid credentials";
//    }
	 
	 public ApiResponse<UserResponseDTO> login(LoginRequest request) {
		    Optional<User> userOpt = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

		    if (!userOpt.isPresent()) {
		        throw new RuntimeException("Invalid credentials");
		    }

		    UserResponseDTO responseDTO = modelMapper.map(userOpt.get(), UserResponseDTO.class);

		    return new ApiResponse<>("Login successful", 200, responseDTO);
		}


    
    
}
