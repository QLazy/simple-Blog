package com.example.myBlog.excuption;

public class CustomizeExcuption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private int code;

	public CustomizeExcuption(CustomizeErrorCode errorCode) {
		this.message = errorCode.getMassage();
		this.code = errorCode.getCode();
	}
	
	public CustomizeExcuption(int code,String message) {
		this.code =code;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
	
	
	
}
