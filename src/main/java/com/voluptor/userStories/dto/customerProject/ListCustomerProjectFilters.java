package com.voluptor.userStories.dto.customerProject;

public class ListCustomerProjectFilters {
		
	protected ListCustomerProjectFilters(Long customerId, Long projectId)
	{
		this.customerId = customerId;
		this.projectId = projectId;
	}
	
	public static ListCustomerProjectFilters fromCustomerId(Long customerId)
	{
		return new ListCustomerProjectFilters(customerId, null);
	}
	
	public static ListCustomerProjectFilters fromProjectId(Long projectId)
	{
		return new ListCustomerProjectFilters(null, projectId);
	}
	
	Long customerId;
	
	Long projectId;
	
	Boolean isOwner;
	
	Boolean isRemoved = false;

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

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	public Boolean getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	

}
