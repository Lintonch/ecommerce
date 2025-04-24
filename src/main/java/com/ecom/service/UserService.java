package com.ecom.service;

import java.util.List;

import com.ecom.dto.UserRequestDTO;
import com.ecom.model.User;

public interface UserService {
	
	void registerUser(UserRequestDTO userRequestDTO);
	
	List<User> getAllUsers();
	User getUserById(Long id);


}
