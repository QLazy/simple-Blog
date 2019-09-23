package com.example.myBlog.back;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.myBlog.entity.MyQuestion;

@Mapper
public interface questionMapper {

	
	//发布一个问题
	@Insert("insert into "
			+ "user_question("
			+ "id,title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values ("
			+ "myQuestion_Id_Seq.Nextval,#{question.title},#{question.description},"
			+ "#{question.gmtCreate},#{question.gmtModified},#{question.creator},#{question.tag}"
			+ ")")
	public void addQuestion(@Param("question")MyQuestion question); 
	
	// 更新一个问题
	@Update("update user_question set title=#{question.title},description=#{question.description},"
			+ "gmt_modified=#{question.gmtModified},tag=#{question.tag} where id=#{question.id}")
	public void updateQuestion(@Param("question") MyQuestion question);
	
	//分页查询全部数据
	@Select("select * from"
			+"("
			+"select rownum r, t.* from"
			+"(select * from user_question  order by id asc) t "
			+"where rownum<=#{size}"
			+")"
			+"where r>=#{pageStartData}") 
	public List<MyQuestion> findAllQuestion(@Param("pageStartData") int pageStartData,@Param("size") int size);

	//根据ID分页查询相应的数据
	@Select("select * from"
			+"("
			+"select rownum r, t.* from"
			+"(select * from user_question where creator=#{id}) t "
			+"where rownum<=#{size}"
			+")"
			+"where r>=#{pageStartData}") 
	public List<MyQuestion> paginationById(@Param("id")int id, @Param("pageStartData") int pageStartData,
			@Param("size") int size);
	
	//统计全部问题数量
	@Select("select count(1) from user_question")
	public int countQuestion();
	
	//根据ID统计相应的问题数量
	@Select("select count(1) from user_question where creator=#{id}")
	public int countQuestionById(@Param("id")int id);

	//根据ID查找相应的问题
	@Select("select * from user_question where id=#{id}")
	public MyQuestion findQuestionById(@Param("id")int id);
}
