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
import com.example.myBlog.entity.MyQuestion;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyCommentExtMapper;
import com.example.myBlog.mapper.MyCommentMapper;
import com.example.myBlog.mapper.MyQuestionExtMapper;
import com.example.myBlog.mapper.MyQuestionMapper;
import com.example.myBlog.mapper.MyUserMapper;

@Service
public class CommentService {

	@Autowired
	private MyCommentMapper commentMapper;

	@Autowired
	private MyCommentExtMapper commentExtMapper;
	
	@Autowired
	private MyQuestionMapper questionMapper;

	@Autowired
	private MyQuestionExtMapper questionExtMapper;

	@Autowired
	private MyUserMapper userMapper;

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
			//增加回复评论数量显示
			if (dbComment == null) {
				throw new CustomizeExcuption(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
//			MyComment myComment = new MyComment();
//			myComment.setId(dbComment.getParentId());
			dbComment.setCommentCount(1);
			commentExtMapper.updateCommentCount(dbComment);
		} else {
			// 回复问题
			MyQuestion dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
			if (dbQuestion == null) {
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			dbQuestion.setCommentCount(1);
			questionExtMapper.updateCommentCount(dbQuestion);
		}
		commentMapper.insertSelective(comment);
	}

	public List<CommentDTO> queryCommentByType(int id ,CommentTypeEnum type) {
		List<Integer> userIds = new ArrayList<>();

		//根据问题id查询全部评论
		MyCommentExample commentExample = new MyCommentExample();
		commentExample.createCriteria().andParentIdEqualTo(id).andParentTypeEqualTo(type.getType());
		if(type == CommentTypeEnum.QUESTION) {
			commentExample.setOrderByClause("gmt_create desc");
		}else if(type == CommentTypeEnum.COMMENT) {
			commentExample.setOrderByClause("gmt_create asc");
		}
		List<MyComment> comments = commentMapper.selectByExample(commentExample);

		//获取全部评论用户ID，去重
		Set<Integer> commentator = comments.stream().map(comment -> comment.getCommentator())
				.collect(Collectors.toSet());
		userIds.addAll(commentator);

		//判断是否有评论
		if(userIds.size()==0) {
			return new ArrayList<>();
		}
		
		//查询全部评论用户
		MyUserExample userExample = new MyUserExample();
		userExample.createCriteria().andIdIn(userIds);
		List<MyUser> users = userMapper.selectByExample(userExample);
		
		//将user对象转化成map
		Map<Integer, MyUser> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
		
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
