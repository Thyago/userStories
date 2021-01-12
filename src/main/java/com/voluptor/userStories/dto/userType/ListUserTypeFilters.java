package com.voluptor.userStories.dto.userType;

public class ListUserTypeFilters {
		
	public ListUserTypeFilters(Long projectId)
	{
		this.projectId = projectId;
	}
	
	Long projectId;
	
	String search;
	
	public Long getProjectId() {
		return projectId;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
