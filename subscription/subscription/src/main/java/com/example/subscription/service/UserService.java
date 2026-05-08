package com.example.subscription.service;

import java.util.List;

import com.example.subscription.dto.request.UserRequestDTO;
import com.example.subscription.dto.response.UserResponseDTO;

import jakarta.validation.Valid;

public interface UserService {

	UserResponseDTO createUser(@Valid UserRequestDTO dto);

	List<UserResponseDTO> getAllUsers();

	UserResponseDTO getUserById(Long id);

	UserResponseDTO updateUserById(Long id, @Valid UserRequestDTO dto);

	void deleteUser(Long id);
	
	

}
