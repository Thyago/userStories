package com.voluptor.userStories.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import com.voluptor.userStories.dto.userType.ListUserTypeFilters;
import com.voluptor.userStories.model.UserType;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class UserTypeRepository implements PanacheRepository<UserType> {
	
	public UserType findByProjectIdId(Long projectId, Long id) {
		try {
			return find("id = :id AND projectId = :projectId", Parameters.with("id", id).and("projectId", projectId)).singleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public List<UserType> findByProjectId(Long projectId) {
		return find("projectId = :projectId", Parameters.with("projectId", projectId)).list();
	}
	
	
	public PanacheQuery<UserType> find(ListUserTypeFilters listUserTypeFilters) {
		String query = "";
		Parameters parameters = null;
		
		if (listUserTypeFilters.getSearch() != null) {
			query = "(name LIKE :search)";
			parameters = Parameters.with("name", "%" + listUserTypeFilters.getSearch() + "%");
		}

		return find(query, parameters);
	}
}
