package com.voluptor.userStories.dto.project;

import javax.validation.constraints.NotBlank;


public class CreateProjectRequest {
	
	@NotBlank
	String name;
	
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
