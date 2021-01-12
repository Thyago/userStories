package com.voluptor.userStories.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class CustomerProject {
	@Id
	@Column(name = "customerId", updatable = false, nullable = false)
	Customer customer;
	
	@Id
	@Column(name = "projectId", updatable = false, nullable = false)
	Project project;
	
	@Column(updatable = true, nullable = false)
	boolean isOwner = false;
	
	@CreationTimestamp
	LocalDateTime createdAt;
	
	@UpdateTimestamp
	LocalDateTime updatedAt;
	
	@Column(updatable = true, nullable = true)
	LocalDateTime removedAt;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
