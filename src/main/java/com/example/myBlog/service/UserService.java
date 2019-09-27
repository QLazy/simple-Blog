package com.example.myBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.MyUserExample;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.MyUserExtMapper;
import com.example.myBlog.mapper.MyUserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private MyUserMapper userMapper;

	@Autowired
	private MyUserExtMapper userExtMapper;

	public void insert(MyUser user) {

		MyUserExample userExample = new MyUserExample();
		userExample.createCriteria().andNameEqualTo(user.getName());
		List<MyUser> users = userMapper.selectByExample(userExample);
		if (users.size()==0) {
			userExtMapper.insert(user);
			user.setAvatarUrl("/images/1.jpg");
		} else if (users.get(0).getPassword().equals(user.getPassword())) {
			user.setId(users.get(0).getId());
			userMapper.updateByPrimaryKeySelective(user);
		} else {
			log.error("UserService -> insert -> user.password is fail,{}",user);
			throw new CustomizeExcuption(CustomizeErrorCode.USER_PASSWORD_REPEAT);
		}

	}

}
