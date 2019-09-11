package com.example.myBlog.excuption;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
	
	QUESTION_NOT_FOUND("这个问题不存在或者已被删除，不要难为我了(ｉДｉ)");
	
	private String message;
	
	private CustomizeErrorCode(String message) {
		this.message = message;
	}

	@Override
	public String getMassage() {
		return message;
	}

}
