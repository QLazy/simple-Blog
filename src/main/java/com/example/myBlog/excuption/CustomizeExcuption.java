package com.example.myBlog.excuption;

public class CustomizeExcuption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CustomizeExcuption(CustomizeErrorCode errorCode) {
		this.message = errorCode.getMassage();
	}
	
	public CustomizeExcuption(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	
}
