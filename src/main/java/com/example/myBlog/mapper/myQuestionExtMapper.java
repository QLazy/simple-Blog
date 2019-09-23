package com.example.myBlog.mapper;

import java.util.List;

import com.example.myBlog.entity.myQuestion;

public interface myQuestionExtMapper {
	
	int updateViewCount(myQuestion record);
	int updateCommentCount(myQuestion record);
	List<myQuestion> selectQuestionByTag(myQuestion record);
	int insert(myQuestion record);
    int insertSelective(myQuestion record);
}