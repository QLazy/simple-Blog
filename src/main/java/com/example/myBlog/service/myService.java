package com.example.myBlog.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.entity.myUser;
import com.example.myBlog.mapper.IUser;

@Service
public class myService {
	
	@Autowired
	private IUser user;
	//查询全部的用户
	public List<myUser> queryAllUser() {
		return user.findAllUser();
	}
	//通过ID查询单个用户
	public myUser queryUserById(int id) {
		return user.findUserByID(id);
	}
	//通过Token查询用户（用作校验）
	public myUser queryUserByToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String token = "";
		for(Cookie cookie:cookies) {
			if("token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		return user.findUserByToken(token);
	}
	//查询全部表（测试用）
	public List<String> queryAlltab(){
		return user.findAllTable();
	}
	//增加用户
	public boolean add(myUser myuser) {
		if(queryUserById(myuser.getId())!=null) {
			return false;
		}
		user.insert(myuser);
		return true;
	}
	//更新用户
	public boolean update(myUser myuser) {
		if(queryUserById(myuser.getId())==null) {
			return false;
		}
		user.updateUser(myuser);
		return true;
	}
	//删除用户
	public boolean delete(int id) {
		if(queryUserById(id)==null) {
			return false;
		}
		user.delUserById(id);
		return true;
	}
}