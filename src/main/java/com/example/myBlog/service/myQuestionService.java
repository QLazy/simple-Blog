package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.dto.paginationDTO;
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

	// 因为问题不存在重复
	public void addOrUpdate(myQuestion question) {
		if(question.getId()==0) {
			question.setGmtModified(System.currentTimeMillis());
			question.setGmtCreate(System.currentTimeMillis());
			questionmapper.addQuestion(question);
		}else {
			question.setGmtModified(System.currentTimeMillis());
			questionmapper.updateQuestion(question);
		}
	}

	public paginationDTO queryAllQuestion(myUser myuser, int page, int size) {
		int totalCount = 0;

		if (myuser == null) {
			totalCount = questionmapper.countQuestion();
		} else {
			totalCount = questionmapper.countQuestionById(myuser.getId());
		}
		int totalPages = (int) Math.ceil(totalCount * 1.0 / size);

		if (page > totalPages) {
			page = totalPages;
		} else if (page < 1) {
			page = 1;
		}

		int pageStartData = size * (page - 1) + 1;

		// orcal需要传入的是分页开始的数据点到结束的数据位置，不是MySQL的第几页与每页数据量
		List<myQuestion> questions = questionmapper.findAllQuestion(pageStartData, size * page);
		List<questionDTO> questionDTOList = new ArrayList<>();
		paginationDTO paginationDTO = new paginationDTO();

		for (myQuestion question : questions) {

			questionDTO questionDTO = new questionDTO();
			myUser user = usermapper.findUserByID(question.getCreator());
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);
		paginationDTO.pagination(totalPages, page);

		return paginationDTO;
	}

	public questionDTO queryQuestionById(int id) {

		questionDTO questionDTO = new questionDTO();

		myQuestion myquestion = questionmapper.findQuestionById(id);

		BeanUtils.copyProperties(myquestion, questionDTO);
		return questionDTO;
	}

}
