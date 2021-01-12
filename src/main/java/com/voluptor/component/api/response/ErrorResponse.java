package com.voluptor.component.api.response;

import java.util.ArrayList;
import java.util.List;

import com.voluptor.component.api.Error;

public class ErrorResponse {

	public ErrorResponse(List<Error> errors) {
		this.errors = errors;
	}
	
	List<Error> errors;
	
	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	public void addError(Error error) {
		if (errors == null)
			errors = new ArrayList<Error>();
		this.errors.add(error);
	}
	
}
