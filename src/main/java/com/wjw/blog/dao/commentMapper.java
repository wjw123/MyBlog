package com.wjw.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjw.blog.entity.comment;

public interface commentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int insert(comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int insertSelective(comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    comment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int updateByPrimaryKeySelective(comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int updateByPrimaryKey(comment record);
    
    //由文章ID获取所有评论信息
    List<comment> getAllcomments(int id);
    
    //获取某一评论的某一页的信息
    List<comment> getPagecomments(int id,int begin,int PageSize);
    
    //获取该ID下评论数量
    int AllCommentsNumber(int id);
    
    //获取所有评论信息
    List<comment> getAllcommentss(@Param(value="begin") int begin,@Param(value="PageSize") int PageSize);
    
    //获取所有的评论数量
    int AllCommentNumber();
    
    //删除指定文章下的所有评论
    int deleteByArticles(int id);
}