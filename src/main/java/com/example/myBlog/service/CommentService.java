package com.example.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyCommentMapper;
import com.example.myBlog.mapper.myQuestionExtMapper;
import com.example.myBlog.mapper.myQuestionMapper;

@Service
public class CommentService {

	@Autowired
	private MyCommentMapper commentMapper;

	@Autowired
	private myQuestionMapper questionMapper;

	@Autowired
	private myQuestionExtMapper questionExtMapper;

	@Transactional
	public void insert(MyComment comment) {

		if (comment.getParentId() == null || comment.getParentId() == 0) {
			throw new CustomizeExcuption(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}

		if (comment.getParentType() == null || !CommentTypeEnum.isExist(comment.getParentType())) {
			throw new CustomizeExcuption(CustomizeErrorCode.TYPE_PARAM_ERROR);
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

}
