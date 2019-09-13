package com.example.myBlog.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.entity.myUser;
import com.example.myBlog.entity.myUserExample;
import com.example.myBlog.mapper.myUserMapper;

@Service
public class myUserService {

	@Autowired
	private myUserMapper userMapper;

	private myUserExample myUserExample = new myUserExample();

	// 查询全部的用户
	public List<myUser> queryAllUser() {
		myUserExample.createCriteria().andIdIsNotNull();
		List<myUser> users = userMapper.selectByExample(myUserExample);
		return users;
	}

	// 通过ID查询单个用户
	public myUser queryUserById(int id) {
		myUserExample.createCriteria().andIdEqualTo(id);
		List<myUser> myUser = userMapper.selectByExample(myUserExample);
		return myUser.get(0);
	}

	// 通过Token查询用户（用作校验）
	public myUser queryUserByToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		myUserExample.createCriteria().andTokenEqualTo(token);
		List<myUser> users = userMapper.selectByExample(myUserExample);
		return users.get(0);
	}

	// 增加用户
	public boolean add(myUser myuser) {
		if (queryUserById(myuser.getId()) != null) {
			return false;
		}
		userMapper.insertSelective(myuser);
		return true;
	}

	// 更新用户
	public boolean update(myUser myuser) {
		if (queryUserById(myuser.getId()) == null) {
			return false;
		}
		myUserExample.createCriteria().andIdEqualTo(myuser.getId());
		userMapper.updateByExampleSelective(myuser, myUserExample);
		return true;
	}

	// 删除用户
	public boolean delete(int id) {
		if (queryUserById(id) == null) {
			return false;
		}
		myUserExample.createCriteria().andIdEqualTo(id);
		userMapper.deleteByExample(myUserExample);
		return true;
	}
}
