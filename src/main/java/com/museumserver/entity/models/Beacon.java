package com.museumserver.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="beacons")
public class Beacon implements Serializable {


	private static final long serialVersionUID = -4675038336264753747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String mac;
	
	public Beacon() {
		super();
	}
	
	public Beacon(Long id, String mac) {
		super();
		this.id = id;
		this.mac = mac;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}