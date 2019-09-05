package com.example.myBlog.dto;

import com.example.myBlog.entity.myUser;

import lombok.Data;

@Data
public class questionDTO {
	
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
	private myUser user;
}
