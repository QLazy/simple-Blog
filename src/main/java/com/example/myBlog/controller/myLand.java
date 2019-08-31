package com.example.myBlog.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String cheakUser(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		request.getSession().setAttribute("user", user.findUserByID(1));
		String token = UUID.randomUUID().toString();
		response.addCookie(new Cookie("token", token));
		System.out.println(user.findUserByToken(""));
		
		return "redirect:/";
	}
}
