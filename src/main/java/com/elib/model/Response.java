package com.elib.model;

import java.sql.Timestamp;

public class Response {
	private String message;
	private String status;
	private Object data;
	private Timestamp dateTime;

	public Response(String message, String status, Object data, Timestamp dateTime) {
		this.message = message;
		this.status = status;
		this.data = data;
		this.dateTime = dateTime;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
