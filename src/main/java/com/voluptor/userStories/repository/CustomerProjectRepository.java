package com.voluptor.userStories.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import com.voluptor.userStories.dto.customerProject.ListCustomerProjectFilters;
import com.voluptor.userStories.model.CustomerProject;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class CustomerProjectRepository implements PanacheRepository<CustomerProject> {
	
	public CustomerProject findByCustomerIdProjectId(Long customerId, Long projectId) {
		return this.findByCustomerIdProjectId(customerId, projectId, null);
	}
	
	public CustomerProject findByCustomerIdProjectId(Long customerId, Long projectId, Boolean isRemoved) {
		String query = "customerId = :customerId AND projectId = :projectId";
		Parameters parameters = Parameters.with("customerId", customerId).and("projectId", projectId);
		
		if (isRemoved == true) {
			query += " AND removedAt IS NOT NULL";
		} else {
			query += " AND removedAt IS NULL";
		}
		
		try {
			return find(query, parameters).singleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public PanacheQuery<CustomerProject> find(ListCustomerProjectFilters listFilters) {
		String query = "";
		Parameters parameters = null;
		
		if (listFilters.getCustomerId() != null) {
			query += "(customerId = :customerId)";
			parameters = Parameters.with("customerId", listFilters.getCustomerId());
		} else {
			query += "(projectId = :projectId)";
			parameters = Parameters.with("projectId", listFilters.getProjectId());
		}
		
		if (listFilters.getIsOwner() != null) {
			query += " AND isOwner = :isOwner";
			parameters.and("isOwner", listFilters.getIsOwner());
		}
		
		if (listFilters.getIsRemoved() == true) {
			query += " AND removedAt IS NOT NULL";
		} else if (listFilters.getIsRemoved() == false) {
			query += " AND removedAt IS NULL";
		}
		
		return find(query, parameters);
	}
}
