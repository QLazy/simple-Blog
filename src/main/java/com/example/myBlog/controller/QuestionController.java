package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myBlog.dto.QuestionDTO;
import com.example.myBlog.service.myQuestionService;

@Controller
public class QuestionController {

	@Autowired
	private myQuestionService questionService;

	@GetMapping("/question/{id}")
	public String question(@PathVariable(name = "id") int id, Model model, HttpServletRequest request) {

		QuestionDTO questionDTO = questionService.queryQuestionById(id);

		questionService.addViewCount(id);
		
		model.addAttribute("question", questionDTO);

		return "question";
	}
}
