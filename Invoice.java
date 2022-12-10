package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
public class Invoice {
	
	@Id
	String id;
	String invId;
	String reqId;
	String transId;
	String customerId;
	int totalPages;
	double amount;
	String paymentDate;
	String status;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(String id, String invId, String reqId, String transId, String customerId, int totalPages,
			double amount, String paymentDate, String status) {
		super();
		this.id = id;
		this.invId = invId;
		this.reqId = reqId;
		this.transId = transId;
		this.customerId = customerId;
		this.totalPages = totalPages;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invId=" + invId + ", reqId=" + reqId + ", transId=" + transId + ", customerId="
				+ customerId + ", totalPages=" + totalPages + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ ", status=" + status + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
