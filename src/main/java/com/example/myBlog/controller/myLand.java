package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myBlog.Mapper.IUser;

@Controller
public class myLand {
	
	@Autowired
	IUser user;
	
	@RequestMapping("checkUser")
	public String cheakUser(Model model,HttpServletRequest request) {
		request.getSession().setAttribute("user", user.findUserByID(1));
		System.out.println(user.findUserByID(1));
		
		return "redirect:/";
	}
}
