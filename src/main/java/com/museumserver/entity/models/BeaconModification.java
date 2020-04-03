package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "beacon_modifications")
public class BeaconModification implements Serializable {

	private static final long serialVersionUID = -4805807971373971002L;

	@EmbeddedId
	@JsonView(DataViews.DefaultData.class)
	private BeaconModificationId id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "beaconModifications" })
	@ManyToOne
	@JoinColumn(name = "beacon_id", insertable = false, updatable = false)
	@JsonView(DataViews.BeaconData.class)
	private Beacon beacon;
	
	@JsonIgnoreProperties({ "beaconModifications" })
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonView(DataViews.UserData.class)
	private User user;

	public BeaconModification() {
		super();
	}

	public BeaconModification(BeaconModificationId id, String description, Beacon beacon, User user) {
		super();
		this.id = id;
		this.description = description;
		this.beacon = beacon;
		this.user = user;
	}

	public BeaconModificationId getId() {
		return id;
	}

	public void setId(BeaconModificationId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}