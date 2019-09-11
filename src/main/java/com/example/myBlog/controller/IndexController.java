package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.service.myQuestionService;

@Controller
public class IndexController {

	@Autowired
	private myQuestionService questionService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		PaginationDTO pagination = questionService.queryAllQuestion(null, page, size);
		model.addAttribute("pagination", pagination);

		return "index";
	}
}
