package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.dto.QuestionDTO;
import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.entity.myQuestionExample;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.entity.myUserExample;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.myQuestionMapper;
import com.example.myBlog.mapper.myUserMapper;

@Service
public class myQuestionService {

	@Autowired
	private myQuestionMapper questionMapper;

	@Autowired
	private myUserMapper userMapper;

	// 因为问题不存在重复
	public void addOrUpdate(myQuestion question) {
		myQuestionExample myQuestionExample = new myQuestionExample();
		if (question.getId() == 0) {
			question.setGmtModified(System.currentTimeMillis());
			question.setGmtCreate(System.currentTimeMillis());
			questionMapper.insertSelective(question);
		} else {
			question.setGmtModified(System.currentTimeMillis());
			myQuestionExample.createCriteria().andIdEqualTo(question.getId());
			int update = questionMapper.updateByExampleSelective(question, myQuestionExample);
			if(update!=1) {
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
		}
	}

	public PaginationDTO queryAllQuestion(myUser myuser, int page, int size) {
		myQuestionExample myQuestionExample = new myQuestionExample();
		int totalCount = 0;

		if (myuser == null) {
			totalCount = (int) questionMapper.countByExample(new myQuestionExample());
		} else {
			myQuestionExample.createCriteria().andCreatorEqualTo(myuser.getId());
			totalCount = (int) questionMapper.countByExample(myQuestionExample);
		}
		int totalPages = (int) Math.ceil(totalCount * 1.0 / size);

		if (page > totalPages) {
			page = totalPages;
		} else if (page < 1) {
			page = 1;
		}

		int pageStartData = size * (page - 1);

		// orcal需要传入的是分页开始的数据点到结束的数据位置，不是MySQL的第几页与每页数据量
		List<myQuestion> questions = questionMapper.selectByExampleWithRowbounds(myQuestionExample,
				new RowBounds(pageStartData, size));
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		PaginationDTO paginationDTO = new PaginationDTO();

		for (myQuestion question : questions) {

			QuestionDTO questionDTO = new QuestionDTO();

			myUserExample userExample = new myUserExample();
			userExample.createCriteria().andIdEqualTo(question.getCreator());
			List<myUser> users = userMapper.selectByExample(userExample);

			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(users.get(0));
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);
		paginationDTO.pagination(totalPages, page);

		return paginationDTO;
	}

	public QuestionDTO queryQuestionById(int id) {
		myQuestionExample myQuestionExample = new myQuestionExample();
		myUserExample myUserExample = new myUserExample();
		QuestionDTO questionDTO = new QuestionDTO();

		myQuestionExample.createCriteria().andIdEqualTo(id);
		List<myQuestion> questions = questionMapper.selectByExample(myQuestionExample);

		if(questions==null) {
			throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}
		
		BeanUtils.copyProperties(questions.get(0), questionDTO);

		myUserExample.createCriteria().andIdEqualTo(questionDTO.getCreator());
		List<myUser> users = userMapper.selectByExample(myUserExample);

		questionDTO.setUser(users.get(0));

		return questionDTO;
	}

}
