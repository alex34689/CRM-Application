package com.crm.institute.Exception;

public class UsernameOrIdNotFound extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4144942862312107852L;

	public UsernameOrIdNotFound() {
		super("Usuario o Id no encontrado");
	}
	
	public UsernameOrIdNotFound(String message) {
		super(message);
	}

}
