package com.example.myBlog.dto;

import lombok.Data;

@Data
public class QuestionQueryDTO {
	private String search;
	private int size;
	private int page;
}
