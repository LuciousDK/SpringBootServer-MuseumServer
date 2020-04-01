package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "exhibition_modifications")
public class ExhibitionModification implements Serializable {

	private static final long serialVersionUID = 4600823415245810257L;

	@EmbeddedId
	@JsonView(DataViews.DefaultData.class)
	private ExhibitionModificationId id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "exhibitionModifications" })
	@ManyToOne
	@JoinColumn(name = "app_user_id", insertable = false, updatable = false)
	@JsonView(DataViews.AppUserData.class)
	private AppUser appUser;

	@JsonIgnoreProperties({ "exhibitionModifications" })
	@ManyToOne
	@JoinColumn(name = "exhibition_id", insertable = false, updatable = false)
	@JsonView(DataViews.ExhibitionData.class)
	private Exhibition exhibition;

	public ExhibitionModification() {
		super();
	}

	public ExhibitionModification(ExhibitionModificationId id, String description, AppUser appUser,
			Exhibition exhibition) {
		super();
		this.id = id;
		this.description = description;
		this.appUser = appUser;
		this.exhibition = exhibition;
	}

	public ExhibitionModificationId getId() {
		return id;
	}

	public void setId(ExhibitionModificationId id) {
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

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}