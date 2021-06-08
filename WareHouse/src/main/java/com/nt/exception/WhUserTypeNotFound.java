package com.nt.exception;

public class WhUserTypeNotFound extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public WhUserTypeNotFound() {
		super();
	}
	
public WhUserTypeNotFound(String msg) {
		super(msg);
	}
}
