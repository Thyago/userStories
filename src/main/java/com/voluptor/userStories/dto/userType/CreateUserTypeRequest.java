package com.voluptor.userStories.dto.userType;

import javax.validation.constraints.NotBlank;

public class CreateUserTypeRequest {
	
	@NotBlank
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
