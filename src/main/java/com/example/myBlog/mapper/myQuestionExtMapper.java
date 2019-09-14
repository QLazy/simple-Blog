package com.example.myBlog.mapper;

import com.example.myBlog.entity.myQuestion;

public interface myQuestionExtMapper {
	
	int updateViewCount(myQuestion record);
	int updateCommentCount(myQuestion record);
}