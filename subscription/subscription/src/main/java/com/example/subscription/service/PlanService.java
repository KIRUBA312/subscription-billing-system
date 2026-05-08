package com.example.subscription.service;

import java.util.List;

import com.example.subscription.dto.request.PlanRequestDTO;
import com.example.subscription.dto.response.PlanResponseDTO;

import jakarta.validation.Valid;

public interface PlanService {

	PlanResponseDTO createdPlan(@Valid PlanRequestDTO dto);

	List<PlanResponseDTO> getAllPlans();

	PlanResponseDTO getPlanById(Long id);

	PlanResponseDTO updatePlan(Long id, @Valid PlanRequestDTO dto);

	void deletePlan(Long id);

}
