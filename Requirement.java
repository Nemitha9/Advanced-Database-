package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requirement")
public class Requirement {
	
	@Id
	String id;
	String reqId;
	String customerId;
	String transFrom;
	String transTo;
	String pages;
	double price;
	String description;
	String status;
	String uploadedDoc;
	String translatedDoc;
	String custConfirmation;

	
	public Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Requirement(String id, String reqId, String customerId, String transFrom, String transTo, String pages,
			double price, String description, String status, String uploadedDoc, String translatedDoc,
			String custConfirmation) {
		this.id = id;
		this.reqId = reqId;
		this.customerId = customerId;
		this.transFrom = transFrom;
		this.transTo = transTo;
		this.pages = pages;
		this.price = price;
		this.description = description;
		this.status = status;
		this.uploadedDoc = uploadedDoc;
		this.translatedDoc = translatedDoc;
		this.custConfirmation = custConfirmation;
	}


	@Override
	public String toString() {
		return "Requirement [id=" + id + ", reqId=" + reqId + ", customerId=" + customerId + ", transFrom=" + transFrom
				+ ", transTo=" + transTo + ", pages=" + pages + ", price=" + price + ", description=" + description
				+ ", status=" + status + ", uploadedDoc=" + uploadedDoc + ", translatedDoc=" + translatedDoc
				+ ", custConfirmation=" + custConfirmation + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getReqId() {
		return reqId;
	}


	public void setReqId(String reqId) {
		this.reqId = reqId;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getTransFrom() {
		return transFrom;
	}


	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}


	public String getTransTo() {
		return transTo;
	}


	public void setTransTo(String transTo) {
		this.transTo = transTo;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUploadedDoc() {
		return uploadedDoc;
	}


	public void setUploadedDoc(String uploadedDoc) {
		this.uploadedDoc = uploadedDoc;
	}


	public String getTranslatedDoc() {
		return translatedDoc;
	}


	public void setTranslatedDoc(String translatedDoc) {
		this.translatedDoc = translatedDoc;
	}


	public String getCustConfirmation() {
		return custConfirmation;
	}


	public void setCustConfirmation(String custConfirmation) {
		this.custConfirmation = custConfirmation;
	}


}
