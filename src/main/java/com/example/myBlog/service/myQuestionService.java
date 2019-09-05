package com.example.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.mapper.questionMapper;

@Service
public class myQuestionService {
	
	@Autowired
	private questionMapper questionmapper;
	
	//因为问题不存在重复
	public void add(myQuestion mq) {
		questionmapper.addQuestion(mq);
	}
	
}
