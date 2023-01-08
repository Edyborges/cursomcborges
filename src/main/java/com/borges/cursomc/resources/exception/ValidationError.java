package com.borges.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError (String fieldName, String message){
		errors.add(new FieldMessage(fieldName, message));
	}
	
	

	
}
