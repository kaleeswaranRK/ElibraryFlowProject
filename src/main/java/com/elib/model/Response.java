package com.elib.model;

import org.json.JSONObject;

public class Response {
	private String message;
	private String status;
	private JSONObject json;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public Response(String message, String status, JSONObject json) {
		super();
		this.message = message;
		this.status = status;
		this.json = json;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", status=" + status + ", json=" + json + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
