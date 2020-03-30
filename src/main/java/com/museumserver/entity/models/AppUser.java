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
public class AppUser implements Serializable {

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
	@JoinColumn(name = "app_user_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "app_user_id", insertable = true, updatable = true)
	@JsonView(DataViews.DefaultData.class)
	private State state;

	@JsonIgnoreProperties({ "appUser" })
	@OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonView(DataViews.AppUserModificationsData.class)
	private List<AppUserModification> appUserModifications;

	@JsonIgnoreProperties({ "appUser" })
	@OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonView(DataViews.ExhibitionModificationsData.class)
	private List<ExhibitionModification> exhibitionModifications;

	@JsonIgnoreProperties({ "appUser" })
	@OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonView(DataViews.ArtworkModificationsData.class)
	private List<ArtworkModification> artworkModifications;

	@JsonIgnoreProperties({ "appUser" })
	@OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonView(DataViews.BeaconModificationsData.class)
	private List<BeaconModification> beaconModifications;

	@JsonIgnoreProperties({ "appUser" })
	@OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonView(DataViews.MediaModificationsData.class)
	private List<MediaModification> mediaModifications;

	public AppUser() {
		super();
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