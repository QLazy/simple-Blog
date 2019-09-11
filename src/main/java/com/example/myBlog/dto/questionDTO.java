package com.example.myBlog.dto;

import com.example.myBlog.entity.myUser;

import lombok.Data;

@Data
public class questionDTO {
	
	private Integer id;
	private String title;
	private String description;
	private String tag;
	private Long gmtCreate;
	private Long gmtModified;
	private Integer creator;
	private Integer viewCount;
	private Integer commentCount;
	private Integer likeCount;
	private myUser user;
}
