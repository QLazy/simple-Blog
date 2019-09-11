package com.example.myBlog.excuption;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

	QUESTION_NOT_FOUND("这个问题不存在或者已被删除，不要难为我了(ｉДｉ)"), 
	CLIENT_ERROR("可能这次问题是出在你那边了(￣３￣)a"),
	SERVICE_ERROR("服务器在烤壁虎了，等我吃吃完φ(>ω<*)");

	@Override
	public String getMassage() {
		return message;
	}

	private String message;

	private CustomizeErrorCode(String message) {
		this.message = message;
	}

}
