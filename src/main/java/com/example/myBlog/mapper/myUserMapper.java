package com.example.myBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.myBlog.entity.myUser;
import com.example.myBlog.entity.myUserExample;

public interface myUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    long countByExample(myUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int deleteByExample(myUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int insert(myUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int insertSelective(myUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    List<myUser> selectByExample(myUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    myUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int updateByExampleSelective(@Param("record") myUser record, @Param("example") myUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int updateByExample(@Param("record") myUser record, @Param("example") myUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int updateByPrimaryKeySelective(myUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYUSER
     *
     * @mbg.generated Tue Sep 10 16:48:14 CST 2019
     */
    int updateByPrimaryKey(myUser record);
}