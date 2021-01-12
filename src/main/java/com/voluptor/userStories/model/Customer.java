package com.voluptor.userStories.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Customer {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
