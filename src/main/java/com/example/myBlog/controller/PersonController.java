package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.service.QuestionService;

@Controller
public class PersonController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/person/{action}")
	public String person(@PathVariable("action") String action, Model model, HttpServletRequest request,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		if ("myQuestions".equals(action)) {
			model.addAttribute("section", "myQuestions");
			model.addAttribute("sectionName", "我的提问");
			myUser myuser = (myUser) request.getSession().getAttribute("user");
			if(myuser==null) {
				return "redirect:/";
				
			}
			PaginationDTO pagination = questionService.queryAllQuestion(myuser, page, size);
			model.addAttribute("pagination", pagination);
		} else if ("myReplies".equals(action)) {
			model.addAttribute("section", "myReplies");
			model.addAttribute("sectionName", "最新回复");
		}
		return "person";
	}

}
