package com.example.myBlog.dto;

import lombok.Data;

@Data

public class CommentDTO {
	private int parentId;
	private int parentType;
	private String content;

}
