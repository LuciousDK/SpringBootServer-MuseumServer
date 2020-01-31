package com.museumserver.entity.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="exhibitions")
public class Exhibition implements Serializable {

	private static final long serialVersionUID = 6727941092252539727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column(name="opening_date")
	private Date openingDate;

	@Column(name="closing_date")
	private Date closingDate;

	@Column
	private String location;


	public Exhibition(Long id, String name, Date openingDate, Date closingDate, String location){
		super();
		this.id = id;
		this.name = name;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.location = location;
	}

	public Exhibition() {
		super();
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

	public void setOpeningDate(Date startDate) {
		this.openingDate = startDate;
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

	
}