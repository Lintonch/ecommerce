package com.ecom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecom.dto.UserRequestDTO;
import com.ecom.exception.EmailAlreadyExistsException;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public void registerUser(UserRequestDTO dto) {
		
		userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
	        throw new EmailAlreadyExistsException("Email already exists!");
	    });
		// Set role to USER if null
		String role = StringUtils.hasText(dto.getRole()) ? dto.getRole() : "USER";

		User user = User.builder().name(dto.getName()).email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword())) // TODO:
																											// Encrypt
																											// in future
				.phone(dto.getPhone()).role(role).createdAt(new Date()).updatedAt(new Date()).build();

		userRepository.save(user);
	}


	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		}

}
