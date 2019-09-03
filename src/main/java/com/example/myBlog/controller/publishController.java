package com.example.myBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class publishController {
	
	//get提交就渲染页面
	@GetMapping("/publish")
	public String getPublish() {
		return "publish";
	}
	
	//验证表单
	@PostMapping("/publish")
	public String postPublish() {
		return "publish";
	}
}
