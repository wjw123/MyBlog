package com.wjw.blog.dao;

import com.wjw.blog.entity.album;

public interface albumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int insert(album record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int insertSelective(album record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    album selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int updateByPrimaryKeySelective(album record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    int updateByPrimaryKey(album record);
}