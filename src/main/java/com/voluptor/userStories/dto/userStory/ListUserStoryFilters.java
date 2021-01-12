package com.voluptor.userStories.dto.userStory;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;

public class ListUserStoryFilters {
		
	public ListUserStoryFilters(Long projectId)
	{
		this.projectId = projectId;
	}
	
	Long projectId;
	
	Long sprintId;
	
	Long userTypeId;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer businessValueMin;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer businessValueMax;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer sizeMin;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer sizeMax;
	
	String search;

	public Long getProjectId() {
		return projectId;
	}
	
	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Integer getBusinessValueMin() {
		return businessValueMin;
	}

	public void setBusinessValueMin(Integer businessValueMin) {
		this.businessValueMin = businessValueMin;
	}

	public Integer getBusinessValueMax() {
		return businessValueMax;
	}

	public void setBusinessValueMax(Integer businessValueMax) {
		this.businessValueMax = businessValueMax;
	}

	public Integer getSizeMin() {
		return sizeMin;
	}

	public void setSizeMin(Integer sizeMin) {
		this.sizeMin = sizeMin;
	}

	public Integer getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(Integer sizeMax) {
		this.sizeMax = sizeMax;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
