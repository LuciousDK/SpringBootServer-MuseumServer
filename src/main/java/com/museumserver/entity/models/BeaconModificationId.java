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
public class BeaconModificationId implements Serializable{
	
	private static final long serialVersionUID = -4211518198608038683L;

	@GeneratedValue
	@Column(name = "date")
	@JsonView(DataViews.DefaultData.class)
	private Timestamp date;

	@NotNull
	@Column(name = "administrator_id")
	@JsonView(DataViews.DefaultData.class)
	private Long administratorId;

	@NotNull
	@Column(name = "beacon_id")
	@JsonView(DataViews.DefaultData.class)
	private Long beaconId;
	
	public BeaconModificationId() {
		super();
	}
	
	public BeaconModificationId(Long administratorId, Long beaconId) {
		super();
		this.date = new Timestamp(System.currentTimeMillis());
		this.administratorId = administratorId;
		this.beaconId = beaconId;
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

	public Long getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(Long beaconId) {
		this.beaconId = beaconId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BeaconModificationId))
			return false;
		BeaconModificationId that = (BeaconModificationId) o;
		return Objects.equals(getDate(), that.getDate())
				&& Objects.equals(getAdministratorId(), that.getAdministratorId())
				&& Objects.equals(getBeaconId(), that.getBeaconId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDate(), getAdministratorId(),getBeaconId());
	}
}
