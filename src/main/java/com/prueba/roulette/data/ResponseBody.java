package com.prueba.roulette.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseBody {

	private String status;
	private String message;
	private Map<String, Object> payload;
	
	public ResponseBody() {
		super();
		this.payload = new HashMap<>();
	}

	public ResponseBody(HttpStatus status, String message) {
		super();
		this.status = status.toString();
		this.message = message;
		this.payload = new HashMap<>();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status.toString();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	
	public void addPayload(String field, Object value) {
		this.payload.put(field, value);
	}
}
