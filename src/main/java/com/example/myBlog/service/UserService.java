package com.example.myBlog.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.mapper.MyUserExtMapper;
import com.example.myBlog.mapper.MyUserMapper;

@Service
public class UserService {

	@Autowired
	private MyUserMapper userMapper;
	
	@Autowired
	private MyUserExtMapper userExtMapper;

	private MyUserExample myUserExample = new MyUserExample();

	// 查询全部的用户
	public List<MyUser> queryAllUser() {
		myUserExample.createCriteria().andIdIsNotNull();
		List<MyUser> users = userMapper.selectByExample(myUserExample);
		return users;
	}

	// 通过ID查询单个用户
	public MyUser queryUserById(int id) {
		myUserExample.createCriteria().andIdEqualTo(id);
		List<MyUser> myUser = userMapper.selectByExample(myUserExample);
		return myUser.get(0);
	}

	// 通过Token查询用户（用作校验）
	public MyUser queryUserByToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		myUserExample.createCriteria().andTokenEqualTo(token);
		List<MyUser> users = userMapper.selectByExample(myUserExample);
		return users.get(0);
	}

	// 增加用户
	public boolean add(MyUser myuser) {
		if (queryUserById(myuser.getId()) != null) {
			return false;
		}
		userExtMapper.insertSelective(myuser);
		return true;
	}

	// 更新用户
	public boolean update(MyUser myuser) {
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
