package com.example.subscription.dto.request;

public class SubscriptionRequestDTO {

	private Long userId;
	
	private Long planId;
	
	public SubscriptionRequestDTO() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
}
