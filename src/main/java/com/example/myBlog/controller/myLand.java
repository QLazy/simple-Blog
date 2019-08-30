package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myBlog.entity.User;

@Controller
public class myLand {
	@Autowired
	User user;
	@RequestMapping("checkUser")
	public String cheakUser(Model model,HttpServletRequest request) {
		request.getSession().setAttribute("user", user);
		System.out.println(user);
		
		return "redirect:/";
	}
}
