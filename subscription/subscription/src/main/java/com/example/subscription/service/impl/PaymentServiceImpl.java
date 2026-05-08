package com.example.subscription.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.request.PaymentRequestDTO;
import com.example.subscription.dto.response.PaymentResponseDTO;
import com.example.subscription.entity.Payment;
import com.example.subscription.entity.Subscription;
import com.example.subscription.repository.PaymentRepository;
import com.example.subscription.repository.SubscriptionRepository;
import com.example.subscription.service.PaymentService;

import jakarta.validation.Valid;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public PaymentResponseDTO createPayment(@Valid PaymentRequestDTO dto) {
		// TODO Auto-generated method stub
		Subscription subscription = subscriptionRepository
				.findById(dto.getSubscriptionId())
				.orElseThrow(() -> 
				new RuntimeException("Subscription not found with id: " + dto.getSubscriptionId()));
		Payment payment = new Payment();
		
		payment.setSubscription(subscription);
		payment.setAmount(dto.getAmount());
		payment.setStatus(dto.getStatus());
		payment.setPaymentDate(LocalDate.now());
		
		
		return maptoDto(paymentRepository.save(payment));
	}

	@Override
	public List<PaymentResponseDTO> getAllPayments() {
		// TODO Auto-generated method stub
		
		return paymentRepository.findAll().stream()
				.map(this::maptoDto).toList();
		
	}

	@Override
	public PaymentResponseDTO getPaymentById(Long id) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment not found"));
		
		return maptoDto(payment);
	}

	@Override
	public PaymentResponseDTO updatePayment(Long id, @Valid PaymentRequestDTO dto) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment not found"));
		
		Subscription subscription = subscriptionRepository
				.findById(dto.getSubscriptionId())
				.orElseThrow(() -> new RuntimeException("subscription not found"));
	
		payment.setSubscription(subscription);
		payment.setAmount(dto.getAmount());
		payment.setStatus(dto.getStatus());
		
		return maptoDto(paymentRepository.save(payment));
	}

	@Override
	public void deletePayment(Long id) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment not found"));
		
		paymentRepository.delete(payment);
		
	}
	
	private PaymentResponseDTO maptoDto(Payment payment) {
		
		PaymentResponseDTO dto = new PaymentResponseDTO();
		
		dto.setId(payment.getId());
		dto.setAmount(payment.getAmount());
		dto.setPaymentDate(payment.getPaymentDate());
		dto.setStatus(payment.getStatus());
		dto.setUserName(
				payment.getSubscription().getUser()
				.getName());
		dto.setPlanName(
				payment.getSubscription().getPlan()
				.getName()
				);
		
		return dto;
	}
	
	
	
}
