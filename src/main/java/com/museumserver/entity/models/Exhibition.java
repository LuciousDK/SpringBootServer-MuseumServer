package com.museumserver.entity.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "exhibitions")
public class Exhibition implements Serializable {

	private static final long serialVersionUID = 6727941092252539727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String name;

	@Column(name = "opening_date")
	@JsonView(DataViews.DefaultData.class)
	private Date openingDate;

	@Column(name = "closing_date")
	@JsonView(DataViews.DefaultData.class)
	private Date closingDate;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String location;

	@ManyToOne
	@JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private State state;

	@JsonIgnoreProperties({ "exhibitions" })
	@OneToMany(mappedBy = "exhibition", fetch = FetchType.LAZY)
	@JsonView(DataViews.ArtworkListData.class)
	private List<Artwork> artworks;

	@ManyToMany
	@JsonIgnoreProperties({ "exhibitions" })
	@JoinTable(name = "exhibition_media", joinColumns = @JoinColumn(name = "exhibition_id"), inverseJoinColumns = @JoinColumn(name = "media_id"))
	@JsonView(DataViews.MediaListData.class)
	private List<Media> media;

	@JsonIgnoreProperties({ "exhibition" })
	@OneToMany(mappedBy = "exhibition", fetch = FetchType.LAZY)
	@JsonView(DataViews.ExhibitionModificationsData.class)
	private List<ExhibitionModification> exhibitionModifications;

	public Exhibition() {
		super();
	}

	public Exhibition(Long id, String name, Date openingDate, Date closingDate, String location, State state,
			List<Artwork> artworks, List<Media> media, List<ExhibitionModification> exhibitionModifications) {
		super();
		this.id = id;
		this.name = name;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.location = location;
		this.state = state;
		this.artworks = artworks;
		this.media = media;
		this.exhibitionModifications = exhibitionModifications;
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

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Artwork> getArtworks() {
		return artworks;
	}

	public void setArtworks(List<Artwork> artworks) {
		this.artworks = artworks;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public List<ExhibitionModification> getExhibitionModifications() {
		return exhibitionModifications;
	}

	public void setExhibitionModifications(List<ExhibitionModification> exhibitionModifications) {
		this.exhibitionModifications = exhibitionModifications;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		String result ="";
		result += "ID: "+this.id+"\n";
		result += "Name: "+this.name+"\n";
		result += "Opening Date: "+this.openingDate+"\n";
		result += "Closing Date: "+this.closingDate+"\n";
		result += "Location: "+this.location+"\n";
		return result;
	}
	
	public String toJSON() {
		String result = "{" + "\"id\":" + this.id +
						",\"name\":\""+this.name+
						"\",\"openingDate\":\""+this.openingDate+
						"\",\"closingDate\":\""+this.closingDate+
						"\",\"location\":\""+this.location+
						"\",\"state\":\""+this.state.getName()+
						"\"}";
		
		return result;
	}

}