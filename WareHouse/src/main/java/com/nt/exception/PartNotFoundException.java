package com.nt.exception;

public class PartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PartNotFoundException() {
		super();
	}
	
	public PartNotFoundException(String message) {
			super(message);
	}
}
