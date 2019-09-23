package com.example.myBlog.mapper;

import com.example.myBlog.entity.Notification;

public interface NotificationExtMapper {
    int insert(Notification record);
    int insertSelective(Notification record);
}