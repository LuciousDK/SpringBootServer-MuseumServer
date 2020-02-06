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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="administrators")
public class Administrator implements Serializable {

	private static final long serialVersionUID = -8965293009855831647L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String email;
	
	@Column
	private String username;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@JsonIgnore
	@Column
	private String password;
	
	@JsonIgnoreProperties({ "administrators" })
	@OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY)
	private List<BeaconModification> beaconModifications;
	
	@JsonIgnoreProperties({ "administrators" })
	@OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY)
	private List<MediaModification> mediaModifications;
	
	public Administrator() {
		super();
	}

	public Administrator(Long id, String email, String username, String firstName, String lastName, String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}