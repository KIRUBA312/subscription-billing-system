package com.example.subscription.service;

import java.util.List;

import com.example.subscription.dto.request.SubscriptionRequestDTO;
import com.example.subscription.dto.response.SubscriptionResponseDTO;

import jakarta.validation.Valid;

public interface SubscriptionService {

	SubscriptionResponseDTO createSubs(@Valid SubscriptionRequestDTO dto);

	List<SubscriptionResponseDTO> getAllSubs();

	SubscriptionResponseDTO getSubsById(Long id);

	SubscriptionResponseDTO updateSubsById(Long id, @Valid SubscriptionRequestDTO dto);

	void deleteSubs(Long id);

}
