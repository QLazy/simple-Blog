package com.example.myBlog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.myBlog.entity.myQuestion;

@Mapper
public interface questionMapper {

	@Insert("insert into "
			+ "user_question("
			+ "id,title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values ("
			+ "myQuestion_Id_Seq.Nextval,#{question.title},#{question.description},"
			+ "#{question.gmtCreate},#{question.gmtModified},#{question.creator},#{question.tag}"
			+ ")")
	public void addQuestion(@Param("question")myQuestion question); 
	
}
