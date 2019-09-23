package com.example.myBlog.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.mapper.MyUserMapper;

@Component
public class SessionIntercepor implements HandlerInterceptor {

	
	@Autowired
	private MyUserMapper userMapper;
	
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
		MyUserExample myUserExample = new MyUserExample();
		myUserExample.createCriteria().andTokenEqualTo(token);
		List<MyUser> users = userMapper.selectByExample(myUserExample);
		if(users.size()==0) {
			return false;
		}
		request.getSession().setAttribute("user", users.get(0));
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
