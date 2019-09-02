package com.example.myBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.myBlog.entity.myUser;

@Mapper
public interface IUser {
	
	@Select("select * from myuser ")
	public List<myUser> findAllUser();
	
	@Select("select * from myuser where id=#{id}")
	public myUser findUserByID(@Param("id")int id);
	
	@Select("select * from myuser where token=#{token}")	
	public myUser findUserByToken(@Param("token")String token);
	
	@Select("select * from tab ")
	public List<String> findAllTable();
	
	@Insert("insert into myuser(id,name,address,age,token) "+
			"values("
			+ "#{user.id},"
			+ "#{myuser.name},"
			+ "#{myuser.address},"
			+ "#{myuser.age},"
			+ "#{myuser.token}"
			+ ")")
	public void insert(@Param("myuser")myUser myuser);
	
	@Delete("delete from myuser where id = #{id}")
	public void delUserById(@Param("id")int id);
	
	@Update("update myuser set "
			+ "name=#{myuser.name},"
			+ "address=#{myuser.address},"
			+ "age=#{myuser.age} "
			+ "where id = #{myuser.id}")
	public void updateUser(@Param("myuser")myUser myuser);
}
