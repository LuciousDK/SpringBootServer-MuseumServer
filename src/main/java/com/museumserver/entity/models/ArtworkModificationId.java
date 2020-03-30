package com.museumserver.entity.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class ArtworkModificationId implements Serializable{

	private static final long serialVersionUID = -4882200071319231190L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "app_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long appUserId;

	@NotNull
	@Column(name = "artwork_id")
	@JsonView(DataViews.DefaultData.class)
	private Long artworkId;
	
	public ArtworkModificationId() {
		super();
	}

	public ArtworkModificationId(Timestamp date, @NotNull Long appUserId, @NotNull Long artworkId) {
		super();
		this.date = date;
		this.appUserId = appUserId;
		this.artworkId = artworkId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public Long getArtworkId() {
		return artworkId;
	}

	public void setArtworkId(Long artworkId) {
		this.artworkId = artworkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ArtworkModificationId))
			return false;
		ArtworkModificationId that = (ArtworkModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getAppUserId(), that.getAppUserId())
				&& Objects.equals(getArtworkId(), that.getArtworkId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAppUserId(),getArtworkId());
	}
}
