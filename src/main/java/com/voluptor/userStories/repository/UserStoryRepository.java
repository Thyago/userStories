package com.voluptor.userStories.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import com.voluptor.userStories.dto.userStory.ListUserStoryFilters;
import com.voluptor.userStories.model.UserStory;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class UserStoryRepository implements PanacheRepository<UserStory> {
	
	public UserStory findByProjectIdId(Long projectId, Long id) {
		try {
			return find("id = :id AND projectId = :projectId", Parameters.with("id", id).and("projectId", projectId)).singleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public PanacheQuery<UserStory> find(ListUserStoryFilters listUserStoryFilters) {
		String query = "projectId = :projectId";
		Parameters parameters = Parameters.with("projectId", listUserStoryFilters.getProjectId());
		
		if (listUserStoryFilters.getSprintId() != null) {
			query += " AND sprintId = :sprintId";
			parameters.and("sprintId", listUserStoryFilters.getSprintId());
		}
		if (listUserStoryFilters.getUserTypeId() != null) {
			query += " AND userTypeId = :userTypeId";
			parameters.and("userTypeId", listUserStoryFilters.getUserTypeId());
		}
		if (listUserStoryFilters.getBusinessValueMin() != null) {
			query += " AND businessValue >= :businessValueMin";
			parameters.and("businessValueMin", listUserStoryFilters.getBusinessValueMin());
		}
		if (listUserStoryFilters.getBusinessValueMax() != null) {
			query += " AND businessValue <= :businessValueMax";
			parameters.and("businessValueMax", listUserStoryFilters.getBusinessValueMax());
		}
		if (listUserStoryFilters.getSizeMin() != null) {
			query += " AND size >= :sizeMin";
			parameters.and("sizeMin", listUserStoryFilters.getSizeMin());
		}
		if (listUserStoryFilters.getSizeMax() != null) {
			query += " AND size <= :sizeMax";
			parameters.and("sizeMax", listUserStoryFilters.getSizeMax());
		}
		if (listUserStoryFilters.getSearch() != null) {
			query += " AND (whoDescription LIKE :search OR what LIKE :search OR why LIKE :search)";
			parameters.and("search", "%" + listUserStoryFilters.getSearch() + "%");
		}

		return find(query, parameters);
	}
}
