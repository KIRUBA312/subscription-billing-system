package com.example.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscription.dto.request.UserRequestDTO;
import com.example.subscription.dto.response.UserResponseDTO;
import com.example.subscription.service.UserService;
import com.example.subscription.util.ApiResponse;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ApiResponse<UserResponseDTO> createUser(@Valid
			@RequestBody UserRequestDTO dto){
		
		return new ApiResponse<>(true,
				"User created succesfully",
				userService.createUser(dto));
	}
	
	@GetMapping
	public ApiResponse<List<UserResponseDTO>> getAllUsers(){
		
		return new ApiResponse<>(true,
				"users fetched succesfully",
				userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ApiResponse<UserResponseDTO> getUserById(
			@PathVariable Long id){
		
		return new ApiResponse<>(
				true,"user fetched succesfully",
				userService.getUserById(id));
	}
	
	@PutMapping("/{id}")
	public ApiResponse<UserResponseDTO> updateUserById(
			@PathVariable Long id,@Valid @RequestBody UserRequestDTO dto){
		
		return new ApiResponse<>(
				true,"User updated successfully",
				userService.updateUserById(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteUser(@PathVariable Long id){
		
		userService.deleteUser(id);
		return new ApiResponse<>(true,"user deleted",null);
	}
	
	
}
