package com.example.myBlog.enums;

public enum CommentTypeEnum {
	QUESTION(1),
	COMMENT(2),
	REPLY(3);
	
	private int type;

	CommentTypeEnum(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public static boolean isExist(Integer parentType) {
		
		for(CommentTypeEnum type:CommentTypeEnum.values()) {
			if(type.getType()==parentType) {
				return true;
			}
		}
		
		return false;
	}
	
}
