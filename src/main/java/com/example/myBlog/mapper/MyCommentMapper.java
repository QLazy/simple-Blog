package com.example.myBlog.mapper;

import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.MyCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MyCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    long countByExample(MyCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int deleteByExample(MyCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int insert(MyComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int insertSelective(MyComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    List<MyComment> selectByExampleWithRowbounds(MyCommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    List<MyComment> selectByExample(MyCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    MyComment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int updateByExampleSelective(@Param("record") MyComment record, @Param("example") MyCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int updateByExample(@Param("record") MyComment record, @Param("example") MyCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int updateByPrimaryKeySelective(MyComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYCOMMENT
     *
     * @mbg.generated Thu Sep 12 16:42:48 CST 2019
     */
    int updateByPrimaryKey(MyComment record);
}