package com.museumserver.entity.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name="media")
public class Media implements Serializable {

	private static final long serialVersionUID = 5633457465538212654L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column(name = "display_name")
	@JsonView(DataViews.DefaultData.class)
	private String displayName;

	@Column(name = "file_name")
	@JsonView(DataViews.DefaultData.class)
	private String fileName;

	@Column(name = "file_type")
	@JsonView(DataViews.DefaultData.class)
	private String fileType;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String extension;
	
	@JsonIgnoreProperties({ "media" })
	@OneToMany(mappedBy = "media", fetch = FetchType.LAZY)
	@JsonView(DataViews.MediaModificationsData.class)
	private List<MediaModification> modifications;

	@JsonIgnoreProperties({ "media" })
	@JsonView(DataViews.ArtworkListData.class)
	@ManyToMany(mappedBy = "media")
	private List<Artwork> artworks;

	@JsonIgnoreProperties({ "media" })
	@JsonView(DataViews.ExhibitionListData.class)
	@ManyToMany(mappedBy = "media")
	private List<Exhibition> exhibitions;


	public Media(Long id, String displayName, String fileName, String fileType, String extension,
			List<MediaModification> modifications, List<Artwork> artworks, List<Exhibition> exhibitions) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.fileName = fileName;
		this.fileType = fileType;
		this.extension = extension;
		this.modifications = modifications;
		this.artworks = artworks;
		this.exhibitions = exhibitions;
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


	public Media(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public List<MediaModification> getModifications() {
		return modifications;
	}

	public void setModifications(List<MediaModification> modifications) {
		this.modifications = modifications;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toJSON() {
		String result ="{" + "\"id\":" +this.id +
				",\"displayName\":\""+this.displayName+
				"\",\"fileName\":\""+this.fileName+
				"\",\"extension\":\""+this.extension+
				"\",\"fileType\":\""+this.fileType+
				"\"}";
		return result;
	}

	
}