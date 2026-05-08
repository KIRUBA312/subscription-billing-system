package com.example.subscription.service;

import java.util.List;

import com.example.subscription.dto.request.PaymentRequestDTO;
import com.example.subscription.dto.response.PaymentResponseDTO;

import jakarta.validation.Valid;

public interface PaymentService {

	PaymentResponseDTO createPayment(@Valid PaymentRequestDTO dto);

	List<PaymentResponseDTO> getAllPayments();

	PaymentResponseDTO getPaymentById(Long id);

	PaymentResponseDTO updatePayment(Long id, @Valid PaymentRequestDTO dto);

	void deletePayment(Long id);

}
