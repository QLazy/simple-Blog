package com.example.myBlog.mapper;

import com.example.myBlog.entity.myUser;

public interface myUserExtMapper {
	int insert(myUser record);
    int insertSelective(myUser record);
}