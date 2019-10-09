package com.example.myBlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.dto.QuestionDTO;
import com.example.myBlog.enums.CommentTypeEnum;
import com.example.myBlog.service.CommentService;
import com.example.myBlog.service.QuestionService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/question/{id}")
	public String question(@PathVariable(name = "id") int id, Model model, HttpServletRequest request) {
		log.error("1");
		QuestionDTO questionDTO = questionService.queryQuestionById(id);

		List<CommentDTO> comments = commentService.queryCommentByType(id, CommentTypeEnum.QUESTION);

		List<QuestionDTO> releventQuestions = questionService.queryQuestionByTag(questionDTO);
		log.error("2");
		// 增加浏览数显示
		questionService.addViewCount(id);
		model.addAttribute("releventQuestions", releventQuestions);
		model.addAttribute("comments", comments);
		model.addAttribute("question", questionDTO);

		return "question";
	}
}
