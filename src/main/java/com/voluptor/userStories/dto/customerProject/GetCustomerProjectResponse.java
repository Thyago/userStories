package com.voluptor.userStories.dto.customerProject;

import java.time.LocalDateTime;

public class GetCustomerProjectResponse {

	Long customerId;
	
	Long projectId;
	
	boolean isOwner;
	
	LocalDateTime createdAt;
	
	LocalDateTime updatedAt;
	
	LocalDateTime removedAt;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getRemovedAt() {
		return removedAt;
	}

	public void setRemovedAt(LocalDateTime removedAt) {
		this.removedAt = removedAt;
	}
}
