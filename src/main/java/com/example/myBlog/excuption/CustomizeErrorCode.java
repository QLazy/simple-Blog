package com.example.myBlog.excuption;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

	QUESTION_NOT_FOUND(2001,"这个问题不存在或者已被删除，不要难为我了(ｉДｉ)"),
	TARGET_PARAM_NOT_FOUND(2002,"回复的问题已被删除或不存在(>ω･* )ﾉ"),
	NO_LOGIN(2003,"请先登录，不然别人无法知道你是谁哦(๑＞ڡ＜)☆"),
	CLIENT_ERROR(2004,"可能这次问题是出在你那边了(￣３￣)a"),
	TYPE_PARAM_ERROR(2005,"评论类型错误或者不存在"),
	COMMENT_NOT_FOUND(2006,"评论不存在或者已被删除"),
	COMMENT_NOT_NULL(2007,"不写内容或者回复空格的话别人会看不懂的(｡•́︿•̀｡)"),
	SERVICE_ERROR(2008,"服务器在烤壁虎了，等我吃吃完φ(>ω<*)"),
	NOTIFICATION_NOT_FOUND(2009,"对方好像还要斟酌一下(・ω<)"),
	READ_NOTIFICATION_USER_ERROR(2009,"对方好像还要斟酌一下(・ω<)"),
	;

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getCode() {
		return code;
	}

	private String message;
	private int code;

	private CustomizeErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

//	public static boolean isNull(String content) {
//		Pattern compile = Pattern.compile("/^[\\s\\S]*.*[^\\s][\\s\\S]*$/");
//		Matcher matcher = compile.matcher(content);
//		return matcher.matches();
//	}


}
