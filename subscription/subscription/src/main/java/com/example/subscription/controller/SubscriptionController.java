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

import com.example.subscription.dto.request.SubscriptionRequestDTO;
import com.example.subscription.dto.response.SubscriptionResponseDTO;
import com.example.subscription.service.SubscriptionService;
import com.example.subscription.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping
	public ApiResponse<SubscriptionResponseDTO> createSubs(
			@Valid @RequestBody SubscriptionRequestDTO dto){
		
		return new ApiResponse<SubscriptionResponseDTO>(
				true,"subscription created",
				subscriptionService.createSubs(dto));
		
	}
	
	@GetMapping
	public ApiResponse<List<SubscriptionResponseDTO>> getAllSubs(){
		
		return new ApiResponse<List<SubscriptionResponseDTO>>(
				true,"subscriptions fetched",
				subscriptionService.getAllSubs());
	}
	
	@GetMapping("/{id}")
	public ApiResponse<SubscriptionResponseDTO> getSubsById(
			@PathVariable Long id){
		
		return new ApiResponse<SubscriptionResponseDTO>(
				true,"subscripion fetched",
				subscriptionService.getSubsById(id));
	}
	
	@PutMapping("/{id}")
	public ApiResponse<SubscriptionResponseDTO> updateSubsById(
			@PathVariable Long id, 
			@Valid @RequestBody SubscriptionRequestDTO dto){
		
		return new ApiResponse<SubscriptionResponseDTO>(
				true,"subscription updated",
				subscriptionService.updateSubsById(id, dto));		
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteSubs(
			@PathVariable Long id){
		subscriptionService.deleteSubs(id);
		
		return new ApiResponse<String>(
				true,"subscription deleted",null);
	}
	
}
