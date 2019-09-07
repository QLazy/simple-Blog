package com.example.myBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class personController {

	@GetMapping("/person/{action}")
	public String person(@PathVariable("action") String action, Model model) {
		
		if ("myQuestions".equals(action)) {
			model.addAttribute("section", "myQuestions");
			model.addAttribute("sectionName", "我的提问");
		} else if ("myReplies".equals(action)) {
			model.addAttribute("section", "myReplies");
			model.addAttribute("sectionName", "最新回复");
		}

		return "person";
	}

}
