package com.example.myBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	
	@Select("select * from user_question")
	public List<myQuestion> findAllQuestion();
	
	
}
