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

import com.example.subscription.dto.request.RoleRequestDTO;
import com.example.subscription.dto.response.RoleResponseDTO;
import com.example.subscription.service.RoleService;
import com.example.subscription.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ApiResponse<RoleResponseDTO> createRole(@Valid @RequestBody RoleRequestDTO dto){
		return new ApiResponse<>(true,
				"Role created successfully",
				roleService.createRole(dto));
	}
	
	@GetMapping
	public ApiResponse<List<RoleResponseDTO>> getAllRoles(){
		return new ApiResponse<>(true,
				"Roles retrieved successfully",
				roleService.getAllRoles());
	}
	
	@GetMapping("/{id}")
	public ApiResponse<RoleResponseDTO> getRoleById(@PathVariable Long id){
		return new ApiResponse<>(true,
				"Role retrieved successfully",
				roleService.getRoleById(id));
	}
	
	@PutMapping("/{id}")
	public ApiResponse<RoleResponseDTO> updateRole(
			@PathVariable Long id, 
			@Valid @RequestBody RoleRequestDTO dto){
		return new ApiResponse<>(true,
				"Role updated successfully",
				roleService.updateRole(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteRole(@PathVariable Long id){
		
		roleService.deleteRole(id);
		return new ApiResponse<>(true,
				"Role deleted successfully",
				null
				);
	}

}
