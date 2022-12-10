package com.mgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "application")
public class Application {
	
	@Id
	String id;
	String appId;
	String reqId;
	String transId;
	String message;
	String status;
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", appId=" + appId + ", reqId=" + reqId + ", transId=" + transId + ", message="
				+ message + ", status=" + status + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Application(String id, String appId, String reqId, String transId, String message, String status) {
		super();
		this.id = id;
		this.appId = appId;
		this.reqId = reqId;
		this.transId = transId;
		this.message = message;
		this.status = status;
	}

	
	
}
