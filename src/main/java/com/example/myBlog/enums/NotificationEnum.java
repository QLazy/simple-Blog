package com.example.myBlog.enums;

public enum NotificationEnum {
	REPLY_QUESTION(1, "评论了你的问题"), 
	REPLY_COMMENT(2, "回复了你的评论"),
	LIKE_QUESTION(3, "赞同了你的问题"),
	LIKE_COMMENT(4, "赞同了你的评论");
	private int type;
	private String name;

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	private NotificationEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static String nameOfType(int type) {
		for (NotificationEnum notificationEnum : NotificationEnum.values()) {
			if (notificationEnum.getType() == type) {
				return notificationEnum.getName();
			}
		}
		return "";
	}
}
