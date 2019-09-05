package com.example.myBlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myBlog.dto.questionDTO;
import com.example.myBlog.service.myQuestionService;
import com.example.myBlog.service.myUserService;

@Controller
public class indexController {
	
	@Autowired
	private myUserService userService;
	
	
	private myQuestionService questionService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model) {
		
		List<questionDTO> questions = questionService.queryAllQuestion();
		request.getSession().setAttribute("user", userService.queryUserByToken(request));
		
		model.addAttribute("question", questions);
		
		return "index";
	}
}
