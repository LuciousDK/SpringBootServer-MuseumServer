package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "user_modifications")
public class UserModification implements Serializable {

	private static final long serialVersionUID = -2994725745901939416L;

	@EmbeddedId
	@JsonView(DataViews.DefaultData.class)
	private UserModificationId id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "userModifications" })
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonView(DataViews.UserData.class)
	private User user;

	@JsonIgnoreProperties({ "userModifications" })
	@ManyToOne
	@JoinColumn(name = "modified_user_id", insertable = false, updatable = false)
	@JsonView(DataViews.UserData.class)
	private User modifiedUser;

	public UserModification() {
		super();
	}

	public UserModification(UserModificationId id, String description, User user, User modifiedUser) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.modifiedUser = modifiedUser;
	}

	public UserModificationId getId() {
		return id;
	}

	public void setId(UserModificationId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(User modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}