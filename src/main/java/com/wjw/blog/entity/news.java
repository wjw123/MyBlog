package com.wjw.blog.entity;

import java.util.Date;

public class news {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_news.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_news.name
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_news.content
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_news.time
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Date time;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_news.type
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_news.id
     *
     * @return the value of tb_news.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_news.id
     *
     * @param id the value for tb_news.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_news.name
     *
     * @return the value of tb_news.name
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_news.name
     *
     * @param name the value for tb_news.name
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_news.content
     *
     * @return the value of tb_news.content
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_news.content
     *
     * @param content the value for tb_news.content
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_news.time
     *
     * @return the value of tb_news.time
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_news.time
     *
     * @param time the value for tb_news.time
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_news.type
     *
     * @return the value of tb_news.type
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_news.type
     *
     * @param type the value for tb_news.type
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }
}