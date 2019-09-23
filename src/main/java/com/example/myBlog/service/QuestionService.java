package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.myBlog.mapper.myQuestionExtMapper;
import com.example.myBlog.mapper.myQuestionMapper;
import com.example.myBlog.mapper.myUserMapper;

@Service
public class QuestionService {

	@Autowired
	private myQuestionMapper questionMapper;

	@Autowired
	private myUserMapper userMapper;

	@Autowired
	private myQuestionExtMapper questionExtMapper;

	// 因为问题不存在重复
	public void addOrUpdate(myQuestion question) {
		myQuestionExample myQuestionExample = new myQuestionExample();
		if (question.getId() == 0) {
			question.setGmtModified(System.currentTimeMillis());
			question.setGmtCreate(System.currentTimeMillis());
			question.setLikeCount(0);
			question.setViewCount(0);
			question.setCommentCount(0);
			questionExtMapper.insert(question);
		} else {
			question.setGmtModified(System.currentTimeMillis());
			myQuestionExample.createCriteria().andIdEqualTo(question.getId());
			int update = questionMapper.updateByExampleSelective(question, myQuestionExample);
			if (update != 1) {
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
		}
	}

	// 分页查询全部问题
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

		//根据问题创建时间倒序显示
		myQuestionExample.setOrderByClause("gmt_Create desc");
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

	// 通过id查询相应的问题
	public QuestionDTO queryQuestionById(int id) {
		myUserExample myUserExample = new myUserExample();
		QuestionDTO questionDTO = new QuestionDTO();

		myQuestion question = questionMapper.selectByPrimaryKey(id);

		if (question == null) {
			throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}

		BeanUtils.copyProperties(question, questionDTO);

		myUserExample.createCriteria().andIdEqualTo(questionDTO.getCreator());
		List<myUser> users = userMapper.selectByExample(myUserExample);

		questionDTO.setUser(users.get(0));

		return questionDTO;
	}

	// 增加浏览数
	public void addViewCount(int id) {

		myQuestion question = new myQuestion();

		question.setId(id);
		question.setViewCount(1);

		questionExtMapper.updateViewCount(question);

	}

	// 根据tag模糊匹配问题
	public List<QuestionDTO> queryQuestionByTag(QuestionDTO queryDTO) {
		myQuestion question = new myQuestion();
		question.setId(queryDTO.getId());
		// 处理tag
		String tag = queryDTO.getTag().replaceAll("，", "|");
		question.setTag(tag);
		// 查询相关问题
		List<myQuestion> questions = questionExtMapper.selectQuestionByTag(question);
		List<QuestionDTO> questionDTOList = questions.stream().map(p -> {
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(p, questionDTO);
			return questionDTO;
		}).collect(Collectors.toList());
		return questionDTOList;
	}

}
