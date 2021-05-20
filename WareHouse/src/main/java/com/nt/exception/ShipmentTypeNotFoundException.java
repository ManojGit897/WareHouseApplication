package com.nt.exception;

public class ShipmentTypeNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public ShipmentTypeNotFoundException() {
		super();
	}

	public ShipmentTypeNotFoundException(String message) {
		super(message);
	}
	
}
