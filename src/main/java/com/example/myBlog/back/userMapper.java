package com.example.myBlog.back;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.myBlog.entity.MyUser;

@Mapper
public interface userMapper {
	
	@Select("select * from myuser ")
	public List<MyUser> findAllUser();
	
	@Select("select * from myuser where id=#{id}")
	public MyUser findUserByID(@Param("id")int id);
	
	@Select("select * from myuser where token=#{token}")	
	public MyUser findUserByToken(@Param("token")String token);
	
	@Select("select * from tab ")
	public List<String> findAllTable();
	
	@Insert("insert into myuser(id,name,address,age,token,avatarUrl) "+
			"values("
			+ "Myuser_Id_Seq.Nextval,"
			+ "#{myuser.name},"
			+ "#{myuser.address},"
			+ "#{myuser.age},"
			+ "#{myuser.token},"
			+ "#{myuser.avatarUrl}"
			+ ")")
	public void insert(@Param("myuser")MyUser myuser);
	
	@Delete("delete from myuser where id = #{id}")
	public void delUserById(@Param("id")int id);
	
	@Update("update myuser set "
			+ "name=#{myuser.name},"
			+ "address=#{myuser.address},"
			+ "age=#{myuser.age},"
			+ "avatarUrl=#{myuser.avatarUrl} "
			+ "where id = #{myuser.id}")
	public void updateUser(@Param("myuser")MyUser myuser);
}
