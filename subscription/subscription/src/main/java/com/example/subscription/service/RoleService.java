package com.example.subscription.service;

import java.util.List;

import com.example.subscription.dto.request.RoleRequestDTO;
import com.example.subscription.dto.response.RoleResponseDTO;

import jakarta.validation.Valid;

public interface RoleService {

	RoleResponseDTO createRole(@Valid RoleRequestDTO dto);

	List<RoleResponseDTO> getAllRoles();

	RoleResponseDTO getRoleById(Long id);

	RoleResponseDTO updateRole(Long id, @Valid RoleRequestDTO dto);

	void deleteRole(Long id);

}
