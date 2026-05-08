package com.example.subscription.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.request.SubscriptionRequestDTO;
import com.example.subscription.dto.response.SubscriptionResponseDTO;
import com.example.subscription.entity.Plan;
import com.example.subscription.entity.Subscription;
import com.example.subscription.entity.User;
import com.example.subscription.repository.PlanRepository;
import com.example.subscription.repository.SubscriptionRepository;
import com.example.subscription.repository.UserRepository;
import com.example.subscription.service.SubscriptionService;

import jakarta.validation.Valid;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlanRepository planRepository;

	@Override
	public SubscriptionResponseDTO createSubs(@Valid SubscriptionRequestDTO dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		Plan plan = planRepository.findById(dto.getPlanId())
				.orElseThrow(() -> new RuntimeException(" plan not found"));
		
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setPlan(plan);
		subscription.setStartDate(LocalDate.now());
		subscription.setEndDate(LocalDate.now()
				.plusDays(plan.getDurationdays()));
		subscription.setStatus("ACTIVE");
				
		return maptoDto(subscriptionRepository.save(subscription));
	}

	@Override
	public List<SubscriptionResponseDTO> getAllSubs() {
		// TODO Auto-generated method stub
		return subscriptionRepository.findAll()
				.stream().map(this::maptoDto).toList();
	}

	@Override
	public SubscriptionResponseDTO getSubsById(Long id) {
		// TODO Auto-generated method stub
		
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription not found"));
		return maptoDto(subscription);
	}

	@Override
	public SubscriptionResponseDTO updateSubsById(Long id, @Valid SubscriptionRequestDTO dto) {
		// TODO Auto-generated method stub
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription not found"));
		
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		Plan plan = planRepository.findById(dto.getPlanId())
				.orElseThrow(() -> new RuntimeException(" plan not found"));
		
		
		subscription.setUser(user);
		subscription.setPlan(plan);
		subscription.setStartDate(LocalDate.now());
		subscription.setEndDate(LocalDate.now()
				.plusDays(plan.getDurationdays()));
		subscription.setStatus("updated");
		return maptoDto(
				subscriptionRepository.save(subscription)
				);
	}

	@Override
	public void deleteSubs(Long id) {
		// TODO Auto-generated method stub
		
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription not found"));
		
		subscriptionRepository.delete(subscription);
	}
	
	private SubscriptionResponseDTO maptoDto(Subscription subscription) {
		
		SubscriptionResponseDTO dto = new SubscriptionResponseDTO();
		
		dto.setId(subscription.getId());
		
		dto.setUsername(subscription.getUser().getName());
		
		dto.setPlanName(subscription.getPlan().getName());
		
		dto.setStartDate(subscription.getStartDate());
		
		dto.setEndDate(subscription.getEndDate());
		
		dto.setStatus(subscription.getStatus());
		
		return dto;
		
	}
	
}
