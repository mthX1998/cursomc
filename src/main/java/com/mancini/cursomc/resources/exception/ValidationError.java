package com.mancini.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
		
	private List<FieldMessage> errors = new ArrayList<>(); 
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
	 super(status, msg, timeStamp);
	// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return errors;
	}

	public void addError(String fieldname, String message) {
		errors.add(new FieldMessage(fieldname,message));
	}
	
	

}