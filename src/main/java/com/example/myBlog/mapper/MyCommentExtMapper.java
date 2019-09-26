package com.example.myBlog.mapper;

import com.example.myBlog.entity.MyComment;

public interface MyCommentExtMapper {
	//增加评论数
	int updateAddCommentCount(MyComment record);
//	减少评论数
	int updateDownCommentCount(MyComment record);
//	增加点赞数
	int updateAddLikeCount(MyComment record);
}