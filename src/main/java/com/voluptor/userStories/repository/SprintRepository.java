package com.voluptor.userStories.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import com.voluptor.userStories.dto.sprint.ListSprintFilters;
import com.voluptor.userStories.model.Sprint;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class SprintRepository implements PanacheRepository<Sprint> {

	
	public Sprint findByProjectIdId(Long projectId, Long id) {
		try {
			return find("id = :id AND projectId = :projectId", Parameters.with("id", id).and("projectId", projectId)).singleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public List<Sprint> findByProjectId(Long projectId) {
		return find("projectId = :projectId", Parameters.with("projectId", projectId)).list();
	}
	
	
	public PanacheQuery<Sprint> find(ListSprintFilters listSprintFilters) {
		String query = "";
		Parameters parameters = null;
		
		if (listSprintFilters.getSearch() != null) {
			query = "(name LIKE :search)";
			parameters = Parameters.with("name", "%" + listSprintFilters.getSearch() + "%");
		}

		return find(query, parameters);
	}
}
