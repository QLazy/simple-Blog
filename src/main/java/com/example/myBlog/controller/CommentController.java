package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.dto.ResultDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ResponseBody
	@RequestMapping(path = "/comment", method = RequestMethod.POST)
	public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {

		
		MyComment myComment = new MyComment();
//		myUser user = (myUser) request.getSession().getAttribute("user");

//		if(user==null) {
//			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
//		}

		myComment.setParentId(commentDTO.getParentId());
		myComment.setParentType(commentDTO.getParentType());
		myComment.setContent(commentDTO.getContent());
		myComment.setGmtCreate(System.currentTimeMillis());
		myComment.setGmtModified(System.currentTimeMillis());
		myComment.setCommentator(1);
		
		commentService.insert(myComment);

		return ResultDTO.succesOf();
	}

}
