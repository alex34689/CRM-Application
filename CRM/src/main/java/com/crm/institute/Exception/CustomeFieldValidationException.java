package com.crm.institute.Exception;

public class CustomeFieldValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4555831838163694286L;

	private String fieldName;

	public CustomeFieldValidationException(String message, String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

}
