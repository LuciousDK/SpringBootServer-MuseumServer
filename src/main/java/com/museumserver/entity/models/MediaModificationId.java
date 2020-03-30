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
	@Column(name = "app_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long appUserId;

	@NotNull
	@Column(name = "media_id")
	@JsonView(DataViews.DefaultData.class)
	private Long mediaId;
	
	public MediaModificationId() {
		super();
	}
	
	public MediaModificationId(Long appUserId, Long mediaId) {
		super();
		this.date = new Timestamp(System.currentTimeMillis());
		this.appUserId = appUserId;
		this.mediaId = mediaId;
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
				&& Objects.equals(getAppUserId(), that.getAppUserId())
				&& Objects.equals(getMediaId(), that.getMediaId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAppUserId(),getMediaId());
	}
}
