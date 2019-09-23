package com.example.myBlog.dto;

import com.example.myBlog.entity.MyUser;

import lombok.Data;

@Data
public class CommentDTO {

	private Integer id;
	private Integer parentId;
	private Integer parentType;
	private Integer commentator;
	private Integer likeCount;
	private Integer commentCount;
	private String content;
	private Long gmtCreate;
	private Long gmtModified;
	private MyUser user;
	
}
