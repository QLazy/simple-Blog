package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.dto.questionDTO;
import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.mapper.questionMapper;
import com.example.myBlog.mapper.userMapper;

@Service
public class myQuestionService {
	
	@Autowired
	private questionMapper questionmapper;
	
	@Autowired
	private userMapper usermapper;
	//因为问题不存在重复
	public void add(myQuestion mq) {
		questionmapper.addQuestion(mq);
	}
	
	
	public List<questionDTO> queryAllQuestion() {
		
		List<myQuestion> questions = questionmapper.findAllQuestion();
		List<questionDTO> questionDTOList = new ArrayList<>();
		
		for(myQuestion question:questions) {
			questionDTO questionDTO = new questionDTO();
			myUser user= usermapper.findUserByID(question.getCreator());
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		return questionDTOList;
	}
	
}
