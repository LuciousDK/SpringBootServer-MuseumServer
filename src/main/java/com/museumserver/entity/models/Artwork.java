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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "artworks")
public class Artwork implements Serializable {

	private static final long serialVersionUID = 7628166745670913872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String name;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String author;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String country;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String description;

	@JsonIgnoreProperties({ "artworks" })
	@OneToMany(mappedBy = "artwork", fetch = FetchType.LAZY)
	@JsonView(DataViews.BeaconListData.class)
	private List<Beacon> beacons;

	@ManyToMany
	@JsonIgnoreProperties({ "artworks" })
	@JoinTable(name = "artwork_media", joinColumns = @JoinColumn(name = "artwork_id"), inverseJoinColumns = @JoinColumn(name = "media_id"))
	@JsonView(DataViews.MediaListData.class)
	private List<Media> media;

	@JsonIgnoreProperties({ "artworks" })
	@ManyToOne
	@JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
	@JsonView(DataViews.ExhibitionData.class)
	private Exhibition exhibition;

	public Artwork(Long id, String name, String author, String country, String description, List<Beacon> beacons,
			List<Media> media, Exhibition exhibition) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.country = country;
		this.description = description;
		this.beacons = beacons;
		this.media = media;
		this.exhibition = exhibition;
	}

	public List<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

	public Artwork() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toJSON() {
		String result = "{" + "\"id\":" + this.id +
						",\"name\":\""+this.name+
						"\",\"country\":\""+this.country+
						"\",\"author\":\""+this.author+
						"\",\"description\":\""+this.description+
						"\",\"exhibitionId\":";
		
		if(this.exhibition!=null) {
			result+=+this.exhibition.getId()+"}";
		}else {
			result += "\"\"}";
		}

		return result;
	}
	
	public String getMediaJSON() {
		String result = "[";
		int counter =0;
		for(Media media : media) {
			if(counter !=0) {
				result+=",";
			}
			counter++;
			result +=media.toJSON();
		}
		result+="]";
		return result;
	}

}