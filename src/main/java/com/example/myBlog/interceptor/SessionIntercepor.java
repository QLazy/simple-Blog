package com.example.myBlog.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.entity.myUser;
import com.example.myBlog.mapper.userMapper;

@Component
public class SessionIntercepor implements HandlerInterceptor {

	
	@Autowired
	private userMapper usermapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie[] cookies = request.getCookies();
		String token = "";
		for(Cookie cookie:cookies) {
			if("token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		myUser user = usermapper.findUserByToken(token);
		request.getSession().setAttribute("user", user);
		if(user==null) {
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
}
