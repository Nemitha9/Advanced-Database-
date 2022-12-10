package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "settings")
public class Settings {
	
	@Id
	String id;
	double costPerPage;
	
	public Settings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Settings(String id, double costPerPage) {
		this.id = id;
		this.costPerPage = costPerPage;
	}

	@Override
	public String toString() {
		return "Settings [id=" + id + ", costPerPage=" + costPerPage + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCostPerPage() {
		return costPerPage;
	}

	public void setCostPerPage(double costPerPage) {
		this.costPerPage = costPerPage;
	}
	
	
	
}
