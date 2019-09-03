package com.example.myBlog.entity;

import lombok.Data;

@Data
public class myQuestion {
	
	private int id;
	private String title;
	private String description;
	private String tag;
	private Long gmtCreate;
	private Long gmtModified;
	private int creator;
	private int viewCount;
	private int commentCount;
	private int likeCount;
	
}
