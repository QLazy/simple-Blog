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

import com.example.myBlog.dto.CommentCreatorDTO;
import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.MyCommentExample;
import com.example.myBlog.entity.MyQuestion;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.entity.Notification;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.enums.NotificationEnum;
import com.example.myBlog.enums.NotificationStatusEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyCommentExtMapper;
import com.example.myBlog.mapper.MyCommentMapper;
import com.example.myBlog.mapper.MyQuestionExtMapper;
import com.example.myBlog.mapper.MyQuestionMapper;
import com.example.myBlog.mapper.MyUserMapper;
import com.example.myBlog.mapper.NotificationMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

	@Autowired
	private NotificationMapper notificationMapper;

	@Transactional
	public void insert(MyComment comment, MyUser commentator) {

		// 判断id是否为空
		if (comment.getParentId() == null || comment.getParentId() == 0) {
			log.error("CommentService -> insert is error ,{}", comment);
			throw new CustomizeExcuption(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}
		// 判断评论类型是否错误
		if (comment.getParentType() == null || !CommentTypeEnum.isExist(comment.getParentType())) {
			log.error("CommentService -> insert is error ,{}", comment);
			throw new CustomizeExcuption(CustomizeErrorCode.TYPE_PARAM_ERROR);
		}
		// 判断评论内容是否为空或全为空格
		if (comment.getContent().length() == 0) {
			log.error("CommentService -> insert is error ,{}", comment);
			throw new CustomizeExcuption(CustomizeErrorCode.COMMENT_NOT_NULL);
		}

		if (comment.getParentType() == CommentTypeEnum.COMMENT.getType()) {
			// 回复评论
			MyComment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
			// 增加回复评论数量显示
			if (dbComment == null) {
				log.error(
						"CommentService -> insert -> commentMapper.selectByPrimaryKey(comment.getParentId()) is error ,{}",
						comment);
				throw new CustomizeExcuption(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
			dbComment.setCommentCount(1);
			commentExtMapper.updateAddCommentCount(dbComment);

			MyQuestion dbQuestion = questionMapper.selectByPrimaryKey(dbComment.getParentId());
			if (dbQuestion == null) {
				log.error(
						"CommentService -> insert -> questionMapper.selectByPrimaryKey(dbComment.getParentId()) is error ,{}",
						dbComment);
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			// 创建通知
			if (dbComment.getCommentator() != commentator.getId()) {
				createNotify(comment, dbComment.getCommentator(), dbQuestion.getTitle(), commentator.getName(),
						NotificationEnum.REPLY_COMMENT, dbQuestion.getId());
			}

		} else {
			// 回复问题
			MyQuestion dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
			if (dbQuestion == null) {
				log.error(
						"CommentService -> insert -> questionMapper.selectByPrimaryKey(comment.getParentId()) is error ,{}",
						comment);
				throw new CustomizeExcuption(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			dbQuestion.setCommentCount(1);
			questionExtMapper.updateAddCommentCount(dbQuestion);
			// 创建通知
			if (dbQuestion.getCreator() != commentator.getId()) {
				createNotify(comment, dbQuestion.getCreator(), dbQuestion.getTitle(), commentator.getName(),
						NotificationEnum.REPLY_QUESTION, dbQuestion.getId());
			}
		}
		commentMapper.insertSelective(comment);

	}

	private void createNotify(MyComment comment, int receiver, String outerTitle, String notifierName,
			NotificationEnum notificationType, int outerId) {
		Notification notification = new Notification();
		notification.setGmtCreate(System.currentTimeMillis());
		notification.setType(notificationType.getType());
		notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
		notification.setOuterId(outerId);
		notification.setNotifier(comment.getCommentator());
		notification.setReceiver(receiver);
		notification.setNotifierName(notifierName);
		notification.setOuterTitle(outerTitle);
		notificationMapper.insert(notification);
	}

	public List<CommentDTO> queryCommentByType(int id, CommentTypeEnum type) {
		List<Integer> userIds = new ArrayList<>();

		// 根据问题id查询全部评论
		MyCommentExample commentExample = new MyCommentExample();
		commentExample.createCriteria().andParentIdEqualTo(id).andParentTypeEqualTo(type.getType());
		if (type == CommentTypeEnum.QUESTION) {
			commentExample.setOrderByClause("gmt_create desc");
		} else if (type == CommentTypeEnum.COMMENT) {
			commentExample.setOrderByClause("gmt_create asc");
		}
		List<MyComment> comments = commentMapper.selectByExample(commentExample);

		// 获取全部评论用户ID，去重
		Set<Integer> commentator = comments.stream().map(comment -> comment.getCommentator())
				.collect(Collectors.toSet());
		userIds.addAll(commentator);

		// 判断是否有评论
		if (userIds.size() == 0) {
			return new ArrayList<>();
		}

		// 查询全部评论用户
		MyUserExample userExample = new MyUserExample();
		userExample.createCriteria().andIdIn(userIds);
		List<MyUser> users = userMapper.selectByExample(userExample);

		// 将user对象转化成map
		Map<Integer, MyUser> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

		// 对commentDTO赋值返回list对象
		List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			commentDTO.setUser(userMap.get(comment.getCommentator()));
			return commentDTO;
		}).collect(Collectors.toList());

		return commentDTOList;
	}

	public void delect(CommentCreatorDTO commentDTO) {
		int type = commentDTO.getParentType();
		// 判断需要删除的是评论还是问题
		if (type != 0) {
			// 删除评论
			MyCommentExample commentExample = new MyCommentExample();
			commentExample.createCriteria().andIdEqualTo(commentDTO.getId()).andParentTypeEqualTo(type);
			commentMapper.deleteByExample(commentExample);
			// 如果删除的是一级评论，则同时删除其下的全部二级评论
			if (type == 1) {
				MyCommentExample commentExample2 = new MyCommentExample();
				commentExample2.createCriteria().andParentIdEqualTo(commentDTO.getId()).andParentTypeEqualTo(type + 1);
				commentMapper.deleteByExample(commentExample2);
				MyQuestion question = new MyQuestion();
				question.setCommentCount(1);
				question.setId(commentDTO.getParentId());
				questionExtMapper.updateDownCommentCount(question);
				return;
			}
			MyComment comment = new MyComment();
			comment.setCommentCount(1);
			comment.setId(commentDTO.getParentId());
			commentExtMapper.updateDownCommentCount(comment);
		} else {
			// 删除问题
			questionMapper.deleteByPrimaryKey(commentDTO.getId());
		}
	}

	public MyComment like(CommentCreatorDTO commentDTO) {
		// 增加点赞数
		MyComment comment = new MyComment();
		comment.setId(commentDTO.getId());
		comment.setLikeCount(1);
		commentExtMapper.updateAddLikeCount(comment);

		// 查询点赞数
		MyComment myComment = commentMapper.selectByPrimaryKey(commentDTO.getId());

		return myComment;
	}

}
