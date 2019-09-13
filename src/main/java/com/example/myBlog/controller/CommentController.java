package com.example.myBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.mapper.MyCommentMapper;

@Controller
public class CommentController {
	
	@Autowired
	private MyCommentMapper commentMapper;
	
	@RequestMapping(value = "/comment",method = RequestMethod.POST)
	public String post(@RequestBody CommentDTO commentDTO) {
		
		MyComment myComment = new MyComment();
		System.out.println("asfsadf");
		myComment.setParentId(commentDTO.getParentId());
		myComment.setParentType(commentDTO.getParentType());
		myComment.setContent(commentDTO.getContent());
		myComment.setGmtCreate(System.currentTimeMillis());
		myComment.setGmtModified(System.currentTimeMillis());
		myComment.setCommentator(1);
		
		commentMapper.insertSelective(myComment);
		
		return "redirect:/";
	}
	
}
