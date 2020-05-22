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
public class UserModificationId implements Serializable{

	private static final long serialVersionUID = -4698681115300118531L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long userId;

	@NotNull
	@Column(name = "modified_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long modifiedUserId;
	
	public UserModificationId() {
		super();
	}
	
	public UserModificationId(Long userId, Long modifiedUserId) {
		super();
		this.date = new Timestamp(System.currentTimeMillis());
		this.userId = userId;
		this.modifiedUserId = modifiedUserId;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		if (!(o instanceof UserModificationId))
			return false;
		UserModificationId that = (UserModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getUserId(), that.getUserId())
				&& Objects.equals(getModifiedUserId(), that.getModifiedUserId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getUserId(),getModifiedUserId());
	}
}
