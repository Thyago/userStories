package com.voluptor.userStories.dto.userStory;

public class UpdateUserStoryRequest extends CreateUserStoryRequest {
	
	Boolean incrementVersion = false;

	public Boolean getIncrementVersion() {
		return incrementVersion;
	}

	public void setIncrementVersion(Boolean incrementVersion) {
		this.incrementVersion = incrementVersion;
	}
}
