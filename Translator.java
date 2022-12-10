package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "translator")
public class Translator {
	
	@Id
	String id;
	String transId;
	String name;
	String address;
	String mobile;
	String emailId;
	String languages;
	String certificate;
	double costPerPage;
	String approvalStatus;
	String password;
	public Translator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Translator(String id, String transId, String name, String address, String mobile, String emailId,
			String languages, String certificate, double costPerPage, String approvalStatus, String password) {
		super();
		this.id = id;
		this.transId = transId;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.emailId = emailId;
		this.languages = languages;
		this.certificate = certificate;
		this.costPerPage = costPerPage;
		this.approvalStatus = approvalStatus;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Translator [id=" + id + ", transId=" + transId + ", name=" + name + ", address=" + address + ", mobile="
				+ mobile + ", emailId=" + emailId + ", languages=" + languages + ", certificate=" + certificate
				+ ", costPerPage=" + costPerPage + ", approvalStatus=" + approvalStatus + ", password=" + password
				+ "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public double getCostPerPage() {
		return costPerPage;
	}
	public void setCostPerPage(double costPerPage) {
		this.costPerPage = costPerPage;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
