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

import com.example.subscription.dto.request.PlanRequestDTO;
import com.example.subscription.dto.response.PlanResponseDTO;
import com.example.subscription.service.PlanService;
import com.example.subscription.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@PostMapping
	public ApiResponse<PlanResponseDTO> createPlan(
			@Valid @RequestBody PlanRequestDTO dto){
		
		return new ApiResponse<>(
				true,"Plan created successfully",
				planService.createdPlan(dto));
	}
	
	@GetMapping
	public ApiResponse<List<PlanResponseDTO>> getAllPlans(){
		
		return new ApiResponse<>(
				true,"plans fetched successfully",
				planService.getAllPlans());
				
	}
	
	@GetMapping("/{id}")
	public ApiResponse<PlanResponseDTO> getPlanById(
			@PathVariable Long id){
		
		return new ApiResponse<PlanResponseDTO>(
				true,"plan fetched successfully",
				planService.getPlanById(id));
	}
	
	@PutMapping("/{id}")
	public ApiResponse<PlanResponseDTO> updatePlan(
			@PathVariable Long id,
			@Valid @RequestBody PlanRequestDTO dto){
		
		return new ApiResponse<PlanResponseDTO>(
				true,"plan updated successfully",
				planService.updatePlan(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deletePlan(
			@PathVariable Long id){
		
		planService.deletePlan(id);
		
		return new ApiResponse<String>(
				true,"plan deleted successfully",null);
		
	}
	

}
