package com.example.myBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hello {
	@RequestMapping("/hello")
	public String helloWorld(@RequestParam(name="name") String name,Model model) {
		model.addAttribute("name",name);
		return "hello";
	}
}
