package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myBlog.service.myUserService;

@Controller
public class indexController {
	
	@Autowired
	private myUserService myservice;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		request.getSession().setAttribute("user", myservice.queryUserByToken(request));
		return "index";
	}
}
