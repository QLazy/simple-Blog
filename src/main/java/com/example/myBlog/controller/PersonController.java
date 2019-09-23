package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.service.NotificationService;
import com.example.myBlog.service.QuestionService;

@Controller
public class PersonController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/person/{action}")
	public String person(@PathVariable("action") String action, Model model, HttpServletRequest request,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		MyUser myUser = (MyUser) request.getSession().getAttribute("user");
		@SuppressWarnings("rawtypes")
		PaginationDTO pagination = null;
		if (myUser == null) {
			return "redirect:/";
		}

		if ("myQuestions".equals(action)) {
			model.addAttribute("section", "myQuestions");
			model.addAttribute("sectionName", "我的提问");
			pagination = questionService.queryAllQuestion(myUser, page, size);
		} else if ("myReplies".equals(action)) {
			pagination = notificationService.queryAllNotification(myUser, page, size);
			int unreadCount = notificationService.unreadCount(myUser.getId());
			model.addAttribute("section", "myReplies");
			model.addAttribute("sectionName", "最新回复");
			model.addAttribute("unreadCount", unreadCount);
		}
		model.addAttribute("pagination", pagination);
		return "person";
	}

}
