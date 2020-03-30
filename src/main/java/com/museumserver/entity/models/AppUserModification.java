package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "app_user_modifications")
public class AppUserModification implements Serializable {

	private static final long serialVersionUID = -2994725745901939416L;

	@EmbeddedId
	@JsonView(DataViews.DefaultData.class)
	private AppUserModificationId id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "appUserModifications" })
	@ManyToOne
	@JoinColumn(name = "app_user_id", insertable = false, updatable = false)
	@JsonView(DataViews.AppUserData.class)
	private AppUser appUser;

	@JsonIgnoreProperties({ "appUserModifications" })
	@ManyToOne
	@JoinColumn(name = "modified_user_id", insertable = false, updatable = false)
	@JsonView(DataViews.AppUserData.class)
	private AppUser modifiedUser;

	public AppUserModification() {
		super();
	}

	public AppUserModification(AppUserModificationId id, String description, AppUser appUser, AppUser modifiedUser) {
		super();
		this.id = id;
		this.description = description;
		this.appUser = appUser;
		this.modifiedUser = modifiedUser;
	}

	public AppUserModificationId getId() {
		return id;
	}

	public void setId(AppUserModificationId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public AppUser getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(AppUser modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}