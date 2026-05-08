package com.example.subscription.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.request.RoleRequestDTO;
import com.example.subscription.dto.response.RoleResponseDTO;
import com.example.subscription.entity.Role;
import com.example.subscription.repository.RoleRepository;
import com.example.subscription.service.RoleService;

import jakarta.validation.Valid;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleResponseDTO createRole(@Valid RoleRequestDTO dto) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setName(dto.getName());
		return maptoDto(roleRepository.save(role));
	}

	@Override
	public List<RoleResponseDTO> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll().stream()
				.map(this::maptoDto)
				.toList();
	}

	@Override
	public RoleResponseDTO getRoleById(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found"));
		return maptoDto(role);
	}

	@Override
	public RoleResponseDTO updateRole(Long id, @Valid RoleRequestDTO dto) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found"));
		role.setName(dto.getName());
		
		return maptoDto(roleRepository.save(role));
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found"));
		roleRepository.delete(role);
	}
	
	private RoleResponseDTO maptoDto(Role role) {
		
		RoleResponseDTO dto = new RoleResponseDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		
		return dto;
	}
	
	
	
}
