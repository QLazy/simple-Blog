package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.dto.pageInitDTO;
import com.example.myBlog.service.myQuestionService;
import com.example.myBlog.service.myUserService;

@Controller
public class indexController {

	@Autowired
	private myUserService userService;

	@Autowired
	private myQuestionService questionService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		
		pageInitDTO pageinits = questionService.queryAllQuestion(page,size);
		request.getSession().setAttribute("user", userService.queryUserByToken(request));
		model.addAttribute("pageInits", pageinits);

		return "index";
	}
}
