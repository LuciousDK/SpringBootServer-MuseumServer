package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "artwork_modifications")
public class ArtworkModification implements Serializable {

	private static final long serialVersionUID = -276698143333469785L;

	@EmbeddedId
	@JsonView(DataViews.DefaultData.class)
	private ArtworkModificationId id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "artworkModifications" })
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonView(DataViews.UserData.class)
	private User user;

	@JsonIgnoreProperties({ "userModifications" })
	@ManyToOne
	@JoinColumn(name = "artwork_id", insertable = false, updatable = false)
	@JsonView(DataViews.ArtworkData.class)
	private Artwork artwork;

	public ArtworkModification() {
		super();
	}

	public ArtworkModification(ArtworkModificationId id, String description, User user, Artwork artwork) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.artwork = artwork;
	}

	public ArtworkModificationId getId() {
		return id;
	}

	public void setId(ArtworkModificationId id) {
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

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}