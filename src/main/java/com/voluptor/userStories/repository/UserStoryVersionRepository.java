package com.voluptor.userStories.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import com.voluptor.userStories.model.UserStoryVersion;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class UserStoryVersionRepository implements PanacheRepository<UserStoryVersion> {
	public UserStoryVersion findByProjectIdUserStoryIdId(Long projectId, Long userStoryId, Long id) {
		try {
			return find("id = :id AND userStory.projectId = :projectId AND userStoryId = :userStoryId", Parameters.with("id", id).and("projectId", projectId).and("userStoryId", userStoryId)).singleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public List<UserStoryVersion> findByProjectIdUserStoryId(Long projectId, Long userStoryId) {
		return find("userStoryId = :userStoryId AND userStory.projectId = :projectId", Parameters.with("userStoryId", userStoryId).and("project_id", projectId)).list();
	}
	
	public PanacheQuery<UserStoryVersion> find(Long projectId, Long userStoryId) {
		return find("userStoryId = :userStoryId AND userStory.projectId = :projectId", Parameters.with("userStoryId", userStoryId).and("project_id", projectId));
	}
}
