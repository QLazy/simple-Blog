package com.example.myBlog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.myBlog.entity.myQuestion;

@Mapper
public interface questionMapper {

	@Insert("insert into "
			+ "user_question(title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values ("
			+ "#{mq.title},#{mq.description},#{mq.gmtCreate},#{mq.gmtModified},#{mq.creator},#{mq.tag}"
			+ ")")
	public void addQuestion(@Param("mq")myQuestion mq); 
	
}
