package com.wjw.blog.entity;

public class aritcletype {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_aritcletype.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_aritcletype.typeName
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String typename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_aritcletype.description
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_aritcletype.id
     *
     * @return the value of tb_aritcletype.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_aritcletype.id
     *
     * @param id the value for tb_aritcletype.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_aritcletype.typeName
     *
     * @return the value of tb_aritcletype.typeName
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getTypename() {
        return typename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_aritcletype.typeName
     *
     * @param typename the value for tb_aritcletype.typeName
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_aritcletype.description
     *
     * @return the value of tb_aritcletype.description
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_aritcletype.description
     *
     * @param description the value for tb_aritcletype.description
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}