package com.example.myBlog.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.entity.MyUser;
import com.example.myBlog.service.UserService;

@Controller
public class EntryController {

	@Autowired
	private UserService userService;

	@GetMapping("/checkUser")
	public String signUp(HttpServletRequest request) {
		return "entry";
	}

	@PostMapping("/checkUser")
	public String signIn(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name, @RequestParam("password") String password) {

		String token = UUID.randomUUID().toString();
		response.addCookie(new Cookie("token", token));

		MyUser user = new MyUser();
		user.setName(name);
		user.setPassword(password);
		user.setToken(token);
		userService.insert(user);
		return "redirect:/";
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
}
