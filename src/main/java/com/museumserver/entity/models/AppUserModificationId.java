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
public class AppUserModificationId implements Serializable{

	private static final long serialVersionUID = -4698681115300118531L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "app_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long appUserId;

	@NotNull
	@Column(name = "modified_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long modifiedUserId;
	
	public AppUserModificationId() {
		super();
	}
	
	public AppUserModificationId(Timestamp date, @NotNull Long appUserId, @NotNull Long modifiedUserId) {
		super();
		this.date = date;
		this.appUserId = appUserId;
		this.modifiedUserId = modifiedUserId;
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

	public Long getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(Long modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AppUserModificationId))
			return false;
		AppUserModificationId that = (AppUserModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getAppUserId(), that.getAppUserId())
				&& Objects.equals(getModifiedUserId(), that.getModifiedUserId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAppUserId(),getModifiedUserId());
	}
}
