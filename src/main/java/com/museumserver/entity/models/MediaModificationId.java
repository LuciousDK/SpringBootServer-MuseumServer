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
public class MediaModificationId implements Serializable{
	
	private static final long serialVersionUID = 3833535011161831872L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "administrator_id")
	@JsonView(DataViews.DefaultData.class)
	private Long administratorId;

	@NotNull
	@Column(name = "media_id")
	@JsonView(DataViews.DefaultData.class)
	private Long mediaId;
	
	public MediaModificationId() {
		super();
	}
	
	public MediaModificationId(Long administratorId, Long mediaId) {
		super();
		this.date = new Timestamp(System.currentTimeMillis());
		this.administratorId = administratorId;
		this.mediaId = mediaId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Long administratorId) {
		this.administratorId = administratorId;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MediaModificationId))
			return false;
		MediaModificationId that = (MediaModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getAdministratorId(), that.getAdministratorId())
				&& Objects.equals(getMediaId(), that.getMediaId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAdministratorId(),getMediaId());
	}
}
