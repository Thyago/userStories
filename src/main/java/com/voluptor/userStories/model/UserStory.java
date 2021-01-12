package com.voluptor.userStories.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.voluptor.component.archivable.model.Archivable;

@Entity
public class UserStory implements Archivable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userStorySeq")
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "projectId", updatable = false, nullable = false)
	Project project;
	
	@ManyToOne
	@JoinColumn(name = "sprintId", updatable = true, nullable = true)
	Sprint sprint;	
	
	@ManyToOne
	@JoinColumn(name = "userTypeId", updatable = true, nullable = true)
	UserType userType;
	
	@Column(nullable = true)
	String whoDescription;
	
	@Column(nullable = false)
	String what;
	
	@Column(nullable = false)
	String why;
	
	@Column(nullable = true)
	Integer businessValue;
	
	@Column(nullable = true)
	Integer size;
	
	@Column(nullable = false)
	Integer version = 1;
	
	@Column(nullable = false)
	Boolean isArchived = false;

	@Column(nullable = true)
	@ElementCollection
	List<String> acceptanceCriterias;
	
	@CreationTimestamp
	LocalDateTime createdAt;
	
	@UpdateTimestamp
	LocalDateTime updatedAt;

	@OneToMany(mappedBy = "userStory", fetch = FetchType.LAZY)
	List<UserStoryVersion> userStoryVersions;
	
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

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getWhoDescription() {
		return whoDescription;
	}

	public void setWhoDescription(String whoDescription) {
		this.whoDescription = whoDescription;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public Integer getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(Integer businessValue) {
		this.businessValue = businessValue;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getAcceptanceCriterias() {
		return acceptanceCriterias;
	}

	public void setAcceptanceCriterias(List<String> acceptanceCriterias) {
		this.acceptanceCriterias = acceptanceCriterias;
	}
	
	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<UserStoryVersion> getUserStoryVersions() {
		return userStoryVersions;
	}

	public void setUserStoryVersions(List<UserStoryVersion> userStoryVersions) {
		this.userStoryVersions = userStoryVersions;
	}
}
