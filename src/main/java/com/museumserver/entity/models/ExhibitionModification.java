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

}