package com.museumserver.entity.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "states")
public class State implements Serializable{
	
	private static final long serialVersionUID = -6022388522807831367L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String name;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<AppUser> appUsers;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<Artwork> artworks;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<Exhibition> exhibitions;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<Beacon> beacons;

	public State() {
		super();
	}

	public State(Long id, String name, List<AppUser> appUsers, List<Artwork> artworks, List<Exhibition> exhibitions,
			List<Beacon> beacons) {
		super();
		this.id = id;
		this.name = name;
		this.appUsers = appUsers;
		this.artworks = artworks;
		this.exhibitions = exhibitions;
		this.beacons = beacons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public List<Artwork> getArtworks() {
		return artworks;
	}

	public void setArtworks(List<Artwork> artworks) {
		this.artworks = artworks;
	}

	public List<Exhibition> getExhibitions() {
		return exhibitions;
	}

	public void setExhibitions(List<Exhibition> exhibitions) {
		this.exhibitions = exhibitions;
	}

	public List<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
