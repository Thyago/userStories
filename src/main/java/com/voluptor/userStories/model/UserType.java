package com.voluptor.userStories.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userTypeSeq")
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "projectId", updatable = false, nullable = false)
	Project project;
	
	@Column(nullable = false)
	String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
