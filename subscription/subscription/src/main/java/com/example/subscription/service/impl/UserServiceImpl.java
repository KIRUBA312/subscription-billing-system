package com.example.subscription.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.request.UserRequestDTO;
import com.example.subscription.dto.response.UserResponseDTO;
import com.example.subscription.entity.Role;
import com.example.subscription.entity.User;
import com.example.subscription.repository.RoleRepository;
import com.example.subscription.repository.UserRepository;
import com.example.subscription.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepositor;

	@Override
	public UserResponseDTO createUser(@Valid UserRequestDTO dto) {
		// TODO Auto-generated method stub
		Role role = roleRepositor.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("role not found"));
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(role);
		
		return maptoDto(userRepository.save(user));
		
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll()
				.stream()
				.map(this::maptoDto)
				.toList();
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user not found"));
		
		return maptoDto(user);
	}

	@Override
	public UserResponseDTO updateUserById(Long id, @Valid UserRequestDTO dto) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user not found"));
		
		Role role = roleRepositor.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("role not found"));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(role);
		
		return maptoDto(userRepository.save(user));
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user not found"));
		
		userRepository.delete(user);
	}
	
	private UserResponseDTO maptoDto(User user) {
		
		UserResponseDTO dto = new UserResponseDTO();
		
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRoleName(user.getRole().getName());
		
		return dto;
	}
	
	
}
