package com.example.myBlog.mapper;

import com.example.myBlog.entity.MyComment;

public interface MyCommentExtMapper {
	int updateAddCommentCount(MyComment record);
	int updateDownCommentCount(MyComment record);
}