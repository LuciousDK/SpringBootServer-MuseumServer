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
public class ExhibitionModificationId implements Serializable{

	private static final long serialVersionUID = 1438321980992055760L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "app_user_id")
	@JsonView(DataViews.DefaultData.class)
	private Long appUserId;

	@NotNull
	@Column(name = "exhibition_id")
	@JsonView(DataViews.DefaultData.class)
	private Long exhibitionId;
	
	public ExhibitionModificationId() {
		super();
	}

	public ExhibitionModificationId(Timestamp date, @NotNull Long appUserId, @NotNull Long exhibitionId) {
		super();
		this.date = date;
		this.appUserId = appUserId;
		this.exhibitionId = exhibitionId;
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

	public Long getExhibitionId() {
		return exhibitionId;
	}

	public void setExhibitionId(Long exhibitionId) {
		this.exhibitionId = exhibitionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExhibitionModificationId))
			return false;
		ExhibitionModificationId that = (ExhibitionModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getAppUserId(), that.getAppUserId())
				&& Objects.equals(getExhibitionId(), that.getExhibitionId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAppUserId(),getExhibitionId());
	}
}
