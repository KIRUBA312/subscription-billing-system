package com.example.subscription.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.request.PlanRequestDTO;
import com.example.subscription.dto.response.PlanResponseDTO;
import com.example.subscription.entity.Plan;
import com.example.subscription.repository.PlanRepository;
import com.example.subscription.service.PlanService;

import jakarta.validation.Valid;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlanRepository planRepository;

	@Override
	public PlanResponseDTO createdPlan(@Valid PlanRequestDTO dto) {
		// TODO Auto-generated method stub
		Plan plan = new Plan(); 
		
		plan.setName(dto.getName());
		plan.setPrice(dto.getPrice());
		plan.setDurationdays(dto.getDurationdays());
		
		return maptoDto(planRepository.save(plan));
	}

	@Override
	public List<PlanResponseDTO> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepository.findAll()
				.stream().map(this::maptoDto)
				.toList();
	}

	@Override
	public PlanResponseDTO getPlanById(Long id) {
		// TODO Auto-generated method stub
		Plan plan = planRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Plan not found"));
		
		return maptoDto(plan);
	}

	@Override
	public PlanResponseDTO updatePlan(Long id, @Valid PlanRequestDTO dto) {
		// TODO Auto-generated method stub
		
		Plan plan = planRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Plan not found"));
		
		plan.setName(dto.getName());
		plan.setPrice(dto.getPrice());
		plan.setDurationdays(dto.getDurationdays());
		return maptoDto(planRepository.save(plan));
	}

	@Override
	public void deletePlan(Long id) {
		// TODO Auto-generated method stub
		Plan plan = planRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Plan not found"));
		
		planRepository.delete(plan);
		
	}
	
	private PlanResponseDTO maptoDto(Plan plan) {
		PlanResponseDTO dto = new PlanResponseDTO();
		
		dto.setId(plan.getId());
		dto.setName(plan.getName());
		dto.setPrice(plan.getPrice());
		dto.setDurationDays(plan.getDurationdays());
		
		return dto;
	}
	
}
