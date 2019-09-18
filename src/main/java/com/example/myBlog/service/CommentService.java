package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.MyCommentExample;
import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.entity.myUserExample;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyCommentMapper;
import com.example.myBlog.mapper.myQuestionExtMapper;
import com.example.myBlog.mapper.myQuestionMapper;
import com.example.myBlog.mapper.myUserMapper;

@Service
public class CommentService {

	@Autowired
	private MyCommentMapper commentMapper;

	@Autowired
	private myQuestionMapper questionMapper;

	@Autowired
	private myQuestionExtMapper questionExtMapper;

	@Autowired
	private myUserMapper userMapper;

	@Transactional
	public void insert(MyComment comment) {

		// 判断id是否为空
		if (comment.getParentId() == null || comment.getParentId() == 0) {
			throw new CustomizeExcuption(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}
		// 判断评论类型是否错误
		if (comment.getParentType() == null || !CommentTypeEnum.isExist(comment.getParentType())) {
			throw new CustomizeExcuption(CustomizeErrorCode.TYPE_PARAM_ERROR);
		}
		// 判断评论内容是否为空或全为空格
		if (comment.getContent().length() == 0) {
			throw new CustomizeExcuption(CustomizeErrorCode.COMMENT_NOT_NULL);
		}

		if (comment.getParentType() == CommentTypeEnum.COMMENT.getType()) {
			// 回复评论
			MyComment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
			if (dbComment == null) {
				throw new CustomizeExcuption(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
		} else {
			// 回复问题
			myQuestion dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
			if (dbQuestion == null) {
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			dbQuestion.setCommentCount(1);
			questionExtMapper.updateCommentCount(dbQuestion);
		}
		commentMapper.insertSelective(comment);
	}

	public List<CommentDTO> queryCommentByQuestionId(int id) {
		List<Integer> userIds = new ArrayList<>();

		MyCommentExample example = new MyCommentExample();
		example.createCriteria().andParentIdEqualTo(id).andParentTypeEqualTo(1);
		List<MyComment> comments = commentMapper.selectByExample(example);

		Set<Integer> commentator = comments.stream().map(comment -> comment.getCommentator())
				.collect(Collectors.toSet());
		userIds.addAll(commentator);

		myUserExample userExample = new myUserExample();
		userExample.createCriteria().andIdIn(userIds);
		List<myUser> users = userMapper.selectByExample(userExample);
		
		//将user对象转化成map
		Map<Integer, myUser> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
		
		//对commentDTO赋值返回list对象
		List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			commentDTO.setUser(userMap.get(comment.getCommentator()));
			return commentDTO;
		}).collect(Collectors.toList());

		return commentDTOList;
	}

}
