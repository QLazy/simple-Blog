package com.example.myBlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myBlog.dto.CommentCreatorDTO;
import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.dto.ResultDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommentController {

	@Autowired
	private CommentService commentService;

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(path = "/comment", method = RequestMethod.POST)
	public ResultDTO post(@RequestBody CommentCreatorDTO commentDTO, HttpServletRequest request) {

		MyComment myComment = new MyComment();
		MyUser user = (MyUser) request.getSession().getAttribute("user");

		if (user == null) {
			log.error("CommentController->post->user is null ,{}",user );
			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
		}

		myComment.setParentId(commentDTO.getParentId());
		myComment.setParentType(commentDTO.getParentType());
		myComment.setContent(commentDTO.getContent());
		myComment.setGmtCreate(System.currentTimeMillis());
		myComment.setGmtModified(System.currentTimeMillis());
		myComment.setCommentator(user.getId());

		commentService.insert(myComment, user);

		return ResultDTO.succesOf("请求成功");
	}

	
	//删除评论
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(path = "/comment/delect", method = RequestMethod.POST)
	public ResultDTO delect(@RequestBody CommentCreatorDTO commentDTO, HttpServletRequest request) {

		MyUser user = (MyUser) request.getSession().getAttribute("user");
		if (user == null) {
			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
		}
		commentService.delect(commentDTO);
		return ResultDTO.succesOf("评论已删除");
	}

	//点赞评论
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(path = "/comment/like", method = RequestMethod.POST)
	public ResultDTO like(@RequestBody CommentCreatorDTO commentDTO, HttpServletRequest request) {

		MyUser user = (MyUser) request.getSession().getAttribute("user");
		if (user == null) {
			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
		}
		MyComment comment = commentService.like(commentDTO);
		return ResultDTO.succesOf(comment);
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(path = "/comment/{id}", method = RequestMethod.GET)
	public ResultDTO<List<CommentDTO>> get(@PathVariable(name = "id") int id, HttpServletRequest request) {

		List<CommentDTO> comments = commentService.queryCommentByType(id, CommentTypeEnum.COMMENT);

		return ResultDTO.succesOf(comments);
	}

}
