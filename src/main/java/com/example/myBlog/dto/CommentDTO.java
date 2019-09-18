package com.example.myBlog.dto;

import com.example.myBlog.entity.myUser;

import lombok.Data;

@Data
public class CommentDTO {

	private Integer id;
	private Integer parentId;
	private Integer parentType;
	private Integer commentator;
	private Integer likeCount;
	private String content;
	private Long gmtCreate;
	private Long gmtModified;
	private myUser user;
	
}
