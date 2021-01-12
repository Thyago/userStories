package com.voluptor.userStories.dto.sprint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateSprintRequest {
	@NotBlank
	String name;
	
	@Min(0)
	Integer number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
