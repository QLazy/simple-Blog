package com.example.myBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class publishController {
	
	@RequestMapping("/publish")
	public String publish() {
		return "publish";
	}
}
