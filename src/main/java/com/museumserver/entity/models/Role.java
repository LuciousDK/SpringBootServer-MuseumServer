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

@Entity(name = "roles")
public class Role implements Serializable{
	
	private static final long serialVersionUID = -1380761174530154736L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataViews.DefaultData.class)
	private Long id;

	@Column
	@JsonView(DataViews.DefaultData.class)
	private String name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<AppUser> appUsers;

	public Role() {
		super();
	}

	public Role(Long id, String name, List<AppUser> appUsers) {
		super();
		this.id = id;
		this.name = name;
		this.appUsers = appUsers;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
