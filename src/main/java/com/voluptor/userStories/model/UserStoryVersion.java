package com.voluptor.userStories.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userStoryId", "version"}))
public class UserStoryVersion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userStorySeq")
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "userStoryId", updatable = false, nullable = false)
	UserStory userStory;
	
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
	Integer version;
	
	@Column(nullable = true)
	@ElementCollection
	List<String> acceptanceCriterias;
	
	@CreationTimestamp
	LocalDateTime createdAt;
	
	@UpdateTimestamp
	LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserStory getUserStory() {
		return userStory;
	}

	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
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
}