package com.wjw.blog.entity;

import java.util.Date;

public class album {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album.photoAddress
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String photoaddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album.photoDescription
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private String photodescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album.photoTimes
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    private Date phototimes;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album.id
     *
     * @return the value of tb_album.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album.id
     *
     * @param id the value for tb_album.id
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album.photoAddress
     *
     * @return the value of tb_album.photoAddress
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getPhotoaddress() {
        return photoaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album.photoAddress
     *
     * @param photoaddress the value for tb_album.photoAddress
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setPhotoaddress(String photoaddress) {
        this.photoaddress = photoaddress == null ? null : photoaddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album.photoDescription
     *
     * @return the value of tb_album.photoDescription
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public String getPhotodescription() {
        return photodescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album.photoDescription
     *
     * @param photodescription the value for tb_album.photoDescription
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setPhotodescription(String photodescription) {
        this.photodescription = photodescription == null ? null : photodescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album.photoTimes
     *
     * @return the value of tb_album.photoTimes
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public Date getPhototimes() {
        return phototimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album.photoTimes
     *
     * @param phototimes the value for tb_album.photoTimes
     *
     * @mbg.generated Sun Jan 13 14:05:29 CST 2019
     */
    public void setPhototimes(Date phototimes) {
        this.phototimes = phototimes;
    }
}