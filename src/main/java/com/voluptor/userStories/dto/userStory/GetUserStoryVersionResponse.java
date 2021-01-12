package com.voluptor.userStories.dto.userStory;

import java.time.LocalDateTime;
import java.util.List;

public class GetUserStoryVersionResponse {
	Long id;
	
	Long userStoryId;
	
	Long userTypeId;
	
	String whoDescription;
	
	String what;
	
	String why;
	
	Integer businessValue;
	
	Integer size;
	
	Integer version;
	
	List<String> acceptanceCriterias;
		
	LocalDateTime createdAt;
	
	LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getWhoDescription() {
		return whoDescription;
	}

	public void setWhoDescription(String whoDescription) {
		this.whoDescription = whoDescription;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public Integer getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(Integer businessValue) {
		this.businessValue = businessValue;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getAcceptanceCriterias() {
		return acceptanceCriterias;
	}

	public void setAcceptanceCriterias(List<String> acceptanceCriterias) {
		this.acceptanceCriterias = acceptanceCriterias;
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
}
