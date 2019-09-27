package com.example.myBlog.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.example.myBlog.dto.QuestionQueryDTO;
import com.example.myBlog.entity.MyQuestion;

public interface MyQuestionExtMapper {
	
	int updateViewCount(MyQuestion record);
	int updateAddCommentCount(MyQuestion record);
	int updateDownCommentCount(MyQuestion record);
	List<MyQuestion> selectQuestionByTag(MyQuestion record);
	int countQuestionBySearch(QuestionQueryDTO record);
	List<MyQuestion> selectBySearch(QuestionQueryDTO example);
	int insert(MyQuestion record);
    int insertSelective(MyQuestion record);
}