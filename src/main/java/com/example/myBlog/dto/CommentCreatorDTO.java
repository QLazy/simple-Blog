package com.example.myBlog.dto;

import lombok.Data;

@Data
public class CommentCreatorDTO {
	private int id;
	private int parentId;
	private int parentType;
	private String content;

}
