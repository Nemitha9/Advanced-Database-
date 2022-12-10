package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deposit")
public class Deposit {
	
	@Id
	String id;
	String depId;
	String invId;
	String transId;
	double totalAmount;
	double amountPaid;
	String depositedDate;
	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Deposit(String id, String depId, String invId, String transId, double totalAmount, double amountPaid,
			String depositedDate) {
		super();
		this.id = id;
		this.depId = depId;
		this.invId = invId;
		this.transId = transId;
		this.totalAmount = totalAmount;
		this.amountPaid = amountPaid;
		this.depositedDate = depositedDate;
	}
	@Override
	public String toString() {
		return "Deposit [id=" + id + ", depId=" + depId + ", invId=" + invId + ", transId=" + transId + ", totalAmount="
				+ totalAmount + ", amountPaid=" + amountPaid + ", depositedDate=" + depositedDate + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getDepositedDate() {
		return depositedDate;
	}
	public void setDepositedDate(String depositedDate) {
		this.depositedDate = depositedDate;
	}
	
	
}
