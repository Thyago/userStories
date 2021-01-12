package com.voluptor.userStories.dto.userStory;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

public class CreateUserStoryRequest {
		
	Long sprintId;
	
	Long userTypeId;
	
	String whoDescription;
	
	@NotEmpty
	String what;
	
	@NotEmpty
	String why;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer businessValue;
	
	@Digits(integer = 10, fraction = 0)
	@PositiveOrZero
	Integer size;
	
	List<String> acceptanceCriterias;
	
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

	public List<String> getAcceptanceCriterias() {
		return acceptanceCriterias;
	}

	public void setAcceptanceCriterias(List<String> acceptanceCriterias) {
		this.acceptanceCriterias = acceptanceCriterias;
	}
}
