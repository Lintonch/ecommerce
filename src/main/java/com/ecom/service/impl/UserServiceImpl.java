package com.ecom.service.impl;

import java.util.Date;

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

	@Override
	@Transactional
	public void registerUser(UserRequestDTO dto) {
		
		userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
	        throw new EmailAlreadyExistsException("Email already exists!");
	    });
		// Set role to USER if null
		String role = StringUtils.hasText(dto.getRole()) ? dto.getRole() : "USER";

		User user = User.builder().name(dto.getName()).email(dto.getEmail()).password(dto.getPassword()) // TODO:
																											// Encrypt
																											// in future
				.phone(dto.getPhone()).role(role).createdAt(new Date()).updatedAt(new Date()).build();

		userRepository.save(user);
	}

}
