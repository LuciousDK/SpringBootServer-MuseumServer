package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "media_modifications")
public class MediaModification implements Serializable {

	private static final long serialVersionUID = 7831655863977697411L;

	@EmbeddedId
	private MediaModificationId id;

	@Column
	private String description;

	
	@JsonIgnoreProperties({ "mediaModifications" })
	@ManyToOne
	@JoinColumn(name = "media_id", insertable = false, updatable = false)
	private Media media;
	
	@JsonIgnoreProperties({ "mediaModifications" })
	@ManyToOne
	@JoinColumn(name = "administrator_id", insertable = false, updatable = false)
	private Administrator administrator;
	
	
	public MediaModificationId getId() {
		return id;
	}

	public void setId(MediaModificationId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MediaModification(MediaModificationId id, String description, Media media, Administrator administrator) {
		super();
		this.id = id;
		this.description = description;
		this.media = media;
		this.administrator = administrator;
	}

	public MediaModification() {
		super();
	}

}