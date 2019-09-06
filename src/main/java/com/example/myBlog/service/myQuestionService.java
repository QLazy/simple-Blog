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
	public void add(myQuestion mq) {
		questionmapper.addQuestion(mq);
	}

	public paginationDTO queryAllQuestion(int page, int size) {

		int pageStartData = size * (page - 1) + 1;

		// orcal需要传入的是分页开始的数据点到结束的数据位置，不是MySQL的第几页与每页数据量
		List<myQuestion> questions = questionmapper.findAllQuestion(pageStartData, size * page);
		List<questionDTO> questionDTOList = new ArrayList<>();
		paginationDTO pageinitDTO = new paginationDTO();
		int totalCount = questionmapper.countQuestionNum();

		for (myQuestion question : questions) {

			questionDTO questionDTO = new questionDTO();
			myUser user = usermapper.findUserByID(question.getCreator());
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}

		pageinitDTO.pagination(totalCount, page, size);

		return pageinitDTO;
	}

}
