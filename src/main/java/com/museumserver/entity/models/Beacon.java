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

@Entity(name="beacons")
public class Beacon implements Serializable {


	private static final long serialVersionUID = -4675038336264753747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String mac;
	
	@JsonIgnoreProperties({ "beacons" })
	@OneToMany(mappedBy = "beacon", fetch = FetchType.LAZY)
	@JsonView(DataViews.BeaconModificationsData.class)
	private List<BeaconModification> modifications;

	@JsonIgnoreProperties({ "beacons" })
	@ManyToOne
	@JoinColumn(name = "artwork_id", insertable = true, updatable = true)
	@JsonView(DataViews.ArtworkData.class)
	private Artwork artwork;
	
	public Beacon() {
		super();
	}
	
	public Beacon(Long id, String mac, List<BeaconModification> modifications, Artwork artwork) {
		super();
		this.id = id;
		this.mac = mac;
		this.modifications = modifications;
		this.artwork = artwork;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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
						",\"mac\":\""+this.mac+
						"\",\"artworkId\":";
		
		if(this.artwork!=null) {
			result+=+this.artwork.getId()+"}";
		}else {
			result += "\"\"}";
		}

		return result;
	}
	
}