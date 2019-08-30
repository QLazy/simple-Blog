package com.example.myBlog.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.myBlog.entity.myUser;

@Mapper
public interface IUser {
	
	@Select("select * from myuser ")
	public List<myUser> findAllUser();
	
	@Select("select * from myuser where id=${id}")
	public myUser findUserByID(@Param("id")int id);
	
	@Select("select * from tab ")
	public List<String> findAllTable();
	
	@Insert("insert into myuser(id,name,address,age) values(${user.id},${user.name},${user.address},${user.age})")
	public void insert(@Param("user")myUser user);
	
	
}
