package com.example.myBlog.entity;

public class MyUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column myuser.id
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column myuser.name
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column myuser.token
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column myuser.avatar_url
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column myuser.password
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column myuser.id
     *
     * @return the value of myuser.id
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column myuser.id
     *
     * @param id the value for myuser.id
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column myuser.name
     *
     * @return the value of myuser.name
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column myuser.name
     *
     * @param name the value for myuser.name
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column myuser.token
     *
     * @return the value of myuser.token
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column myuser.token
     *
     * @param token the value for myuser.token
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column myuser.avatar_url
     *
     * @return the value of myuser.avatar_url
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column myuser.avatar_url
     *
     * @param avatarUrl the value for myuser.avatar_url
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column myuser.password
     *
     * @return the value of myuser.password
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column myuser.password
     *
     * @param password the value for myuser.password
     *
     * @mbg.generated Sat Sep 28 20:31:56 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}