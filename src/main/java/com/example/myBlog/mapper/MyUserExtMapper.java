package com.example.myBlog.mapper;

import com.example.myBlog.entity.MyUser;

public interface MyUserExtMapper {
	int insert(MyUser record);
    int insertSelective(MyUser record);
}