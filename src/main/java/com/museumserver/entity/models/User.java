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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "app_users")
public class User implements Serializable {

	private static final long serialVersionUID = -8965293009855831647L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String email;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String username;

	@Column(name = "first_name")
	@JsonView(DataViews.DefaultData.class)
	private String firstName;

	@Column(name = "last_name")
	@JsonView(DataViews.DefaultData.class)
	private String lastName;

	@JsonIgnore
	@Column
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "state_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private State state;

	@JsonIgnoreProperties({ "user" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonView(DataViews.UserModificationsData.class)
	private List<UserModification> userModifications;

	@JsonIgnoreProperties({ "user" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonView(DataViews.ExhibitionModificationsData.class)
	private List<ExhibitionModification> exhibitionModifications;

	@JsonIgnoreProperties({ "user" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonView(DataViews.ArtworkModificationsData.class)
	private List<ArtworkModification> artworkModifications;

	@JsonIgnoreProperties({ "user" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonView(DataViews.BeaconModificationsData.class)
	private List<BeaconModification> beaconModifications;

	@JsonIgnoreProperties({ "user" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonView(DataViews.MediaModificationsData.class)
	private List<MediaModification> mediaModifications;

	public User() {
		super();
	}

	public User(Long id, String email, String username, String firstName, String lastName, String password,
			Role role, State state, List<UserModification> userModifications,
			List<ExhibitionModification> exhibitionModifications, List<ArtworkModification> artworkModifications,
			List<BeaconModification> beaconModifications, List<MediaModification> mediaModifications) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.state = state;
		this.userModifications = userModifications;
		this.exhibitionModifications = exhibitionModifications;
		this.artworkModifications = artworkModifications;
		this.beaconModifications = beaconModifications;
		this.mediaModifications = mediaModifications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<UserModification> getUserModifications() {
		return userModifications;
	}

	public void setUserModifications(List<UserModification> userModifications) {
		this.userModifications = userModifications;
	}

	public List<ExhibitionModification> getExhibitionModifications() {
		return exhibitionModifications;
	}

	public void setExhibitionModifications(List<ExhibitionModification> exhibitionModifications) {
		this.exhibitionModifications = exhibitionModifications;
	}

	public List<ArtworkModification> getArtworkModifications() {
		return artworkModifications;
	}

	public void setArtworkModifications(List<ArtworkModification> artworkModifications) {
		this.artworkModifications = artworkModifications;
	}

	public List<BeaconModification> getBeaconModifications() {
		return beaconModifications;
	}

	public void setBeaconModifications(List<BeaconModification> beaconModifications) {
		this.beaconModifications = beaconModifications;
	}

	public List<MediaModification> getMediaModifications() {
		return mediaModifications;
	}

	public void setMediaModifications(List<MediaModification> mediaModifications) {
		this.mediaModifications = mediaModifications;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toJSON() {
		String result = "{" + "\"id\":" + this.id +
						",\"firstName\":\""+this.firstName+
						"\",\"lastName\":\""+this.lastName+
						"\",\"username\":\""+this.username+
						"\",\"email\":\""+this.email+
						"\",\"role\":\""+this.role+
						"\",\"state\":\""+this.state+
						"\"}";

		return result;
	}

}