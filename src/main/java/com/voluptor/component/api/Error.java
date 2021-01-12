package com.voluptor.component.api;

public class Error {
	public Error(String error, String message) {
		this.error = error;
		this.message = message;		
	}
	
	public Error(String error, String message, String detail) {
		this(error, message);
		this.detail = detail;
	}
	
	String error;
	
	String message;
	
	String detail;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
