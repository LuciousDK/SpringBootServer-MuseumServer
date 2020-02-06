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
	@JoinColumn(name = "administrator_id", insertable = false, updatable = false)
	@JsonView(DataViews.AdministratorData.class)
	private Administrator administrator;
	
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

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BeaconModification(BeaconModificationId id, String description, Beacon beacon, Administrator administrator) {
		super();
		this.id = id;
		this.description = description;
		this.beacon = beacon;
		this.administrator = administrator;
	}

	public BeaconModification() {
		super();
	}

}