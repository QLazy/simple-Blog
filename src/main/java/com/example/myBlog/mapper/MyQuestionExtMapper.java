package com.example.myBlog.mapper;

import java.util.List;

import com.example.myBlog.entity.MyQuestion;

public interface MyQuestionExtMapper {
	
	int updateViewCount(MyQuestion record);
	int updateCommentCount(MyQuestion record);
	List<MyQuestion> selectQuestionByTag(MyQuestion record);
	int insert(MyQuestion record);
    int insertSelective(MyQuestion record);
}