package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.dto.QuestionDTO;
import com.example.myBlog.dto.QuestionQueryDTO;
import com.example.myBlog.entity.MyQuestion;
import com.example.myBlog.entity.MyQuestionExample;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyQuestionExtMapper;
import com.example.myBlog.mapper.MyQuestionMapper;
import com.example.myBlog.mapper.MyUserMapper;

@Service
public class QuestionService {

	@Autowired
	private MyQuestionMapper questionMapper;

	@Autowired
	private MyUserMapper userMapper;

	@Autowired
	private MyQuestionExtMapper questionExtMapper;

	// 因为问题不存在重复
	public void addOrUpdate(MyQuestion question) {
		MyQuestionExample myQuestionExample = new MyQuestionExample();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaginationDTO queryAllQuestion(MyUser myuser, QuestionQueryDTO queryDTO) {
		int page = queryDTO.getPage();
		int size = queryDTO.getSize();
		String search = queryDTO.getSearch();
		int totalCount = 0;
		if (StringUtils.isNotBlank(search)) {
			search = search.replaceAll(" ", "|");
		}
		MyQuestionExample myQuestionExample = new MyQuestionExample();
		if (myuser == null) {
			if (StringUtils.isNotBlank(search)) {
				totalCount = questionExtMapper.countQuestionBySearch(queryDTO);
			} else {
				totalCount = (int) questionMapper.countByExample(new MyQuestionExample());
			}
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

		page = page == 0 ? 1 : page;
		totalPages = totalPages == 0 ? 1 : totalPages;

		int pageStartData = size * (page - 1);

		// 根据问题创建时间倒序显示
		List<MyQuestion> questions = null;
		if (StringUtils.isNotBlank(queryDTO.getSearch())) {
			queryDTO.setSearch(search);
			questions = questionExtMapper.selectBySearch(queryDTO);
		} else {
			myQuestionExample.setOrderByClause("gmt_Create desc");
			questions = questionMapper.selectByExampleWithRowbounds(myQuestionExample,
					new RowBounds(pageStartData, size));
		}
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		PaginationDTO paginationDTO = new PaginationDTO();

		for (MyQuestion question : questions) {

			QuestionDTO questionDTO = new QuestionDTO();

			MyUserExample userExample = new MyUserExample();
			userExample.createCriteria().andIdEqualTo(question.getCreator());
			List<MyUser> users = userMapper.selectByExample(userExample);

			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(users.get(0));
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setData(questionDTOList);
		paginationDTO.pagination(totalPages, page);

		return paginationDTO;
	}

	// 通过id查询相应的问题
	public QuestionDTO queryQuestionById(int id) {
		MyUserExample myUserExample = new MyUserExample();
		QuestionDTO questionDTO = new QuestionDTO();

		MyQuestion question = questionMapper.selectByPrimaryKey(id);

		if (question == null) {
			throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}

		BeanUtils.copyProperties(question, questionDTO);

		myUserExample.createCriteria().andIdEqualTo(questionDTO.getCreator());
		List<MyUser> users = userMapper.selectByExample(myUserExample);

		questionDTO.setUser(users.get(0));

		return questionDTO;
	}

	// 增加浏览数
	public void addViewCount(int id) {

		MyQuestion question = new MyQuestion();

		question.setId(id);
		question.setViewCount(1);

		questionExtMapper.updateViewCount(question);

	}

	// 根据tag模糊匹配问题
	public List<QuestionDTO> queryQuestionByTag(QuestionDTO queryDTO) {
		MyQuestion question = new MyQuestion();
		question.setId(queryDTO.getId());
		// 处理tag
		String tag = queryDTO.getTag().replaceAll("，", "|");
		question.setTag(tag);
		// 查询相关问题
		List<MyQuestion> questions = questionExtMapper.selectQuestionByTag(question);
		List<QuestionDTO> questionDTOList = questions.stream().map(p -> {
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(p, questionDTO);
			return questionDTO;
		}).collect(Collectors.toList());
		return questionDTOList;
	}

}
