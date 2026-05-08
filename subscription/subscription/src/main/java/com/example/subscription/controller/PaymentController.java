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

import com.example.subscription.dto.request.PaymentRequestDTO;
import com.example.subscription.dto.response.PaymentResponseDTO;
import com.example.subscription.service.PaymentService;
import com.example.subscription.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ApiResponse<PaymentResponseDTO> createPayment(
			@Valid @RequestBody PaymentRequestDTO dto ){
		
		return new ApiResponse<PaymentResponseDTO>(
				true, "payment created successfully",
				paymentService.createPayment(dto));
	}
	
	@GetMapping
	public ApiResponse<List<PaymentResponseDTO>> getAllPayments(){
		
		return new ApiResponse<List<PaymentResponseDTO>>(
				true,"payments fetched successfully",
				paymentService.getAllPayments());
	}
	
	@GetMapping("/{id}")
	public ApiResponse<PaymentResponseDTO> getPaymentsById(
			@PathVariable Long id){
		
		return new ApiResponse<PaymentResponseDTO>(
				true,"payment fetched successfully",
				paymentService.getPaymentById(id));
		
	}
	
	@PutMapping("/id")
	public ApiResponse<PaymentResponseDTO> updatePayment(
			@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO dto){
		
		return new ApiResponse<PaymentResponseDTO>(
				true,"Payment updated successfully",
				paymentService.updatePayment(id,dto));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deletePayment(
			@PathVariable Long id){
		
		paymentService.deletePayment(id);
		
		return new ApiResponse<String>(
				true,"payment deleted successfully",null);
	}
	
}
