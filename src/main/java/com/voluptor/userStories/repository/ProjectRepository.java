package com.voluptor.userStories.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.voluptor.userStories.dto.project.ListProjectFilters;
import com.voluptor.userStories.model.Project;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class ProjectRepository implements PanacheRepository<Project> {
	
	public List<Project> listAll() {
		return find("").list();
	}
	
	public PanacheQuery<Project> find(ListProjectFilters listProjectFilters) {
		if (listProjectFilters.getSearch() != null) {
			String query = "(LOWER(name) LIKE LOWER(:search))";
			Parameters parameters = Parameters.with("search", "%" + listProjectFilters.getSearch() + "%");
			return find(query, parameters);
		}

		return findAll();
	}
}
