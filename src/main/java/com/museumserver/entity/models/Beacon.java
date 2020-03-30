package com.museumserver.entity.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "beacons")
public class Beacon implements Serializable {

	private static final long serialVersionUID = -4675038336264753747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String uuid;

	@JsonIgnoreProperties({ "beacons" })
	@ManyToOne
	@JoinColumn(name = "artwork_id", insertable = true, updatable = true)
	@JsonView(DataViews.ArtworkData.class)
	private Artwork artwork;

	@ManyToOne
	@JoinColumn(name = "beacon_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private State state;

	@JsonIgnoreProperties({ "beacons" })
	@OneToMany(mappedBy = "beacon", fetch = FetchType.LAZY)
	@JsonView(DataViews.BeaconModificationsData.class)
	private List<BeaconModification> modifications;

	public Beacon() {
		super();
	}

	public Beacon(Long id, String uuid, Artwork artwork, State state, List<BeaconModification> modifications) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.artwork = artwork;
		this.state = state;
		this.modifications = modifications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<BeaconModification> getModifications() {
		return modifications;
	}

	public void setModifications(List<BeaconModification> modifications) {
		this.modifications = modifications;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toJSON() {
		String result = "{" + "\"id\":" + this.id +
						",\"uuid\":\""+this.uuid+
						"\",\"artworkId\":";
		
		if(this.artwork!=null) {
			result+=+this.artwork.getId()+"}";
		}else {
			result += "\"\"}";
		}

		return result;
	}

}