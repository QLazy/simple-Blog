package com.example.myBlog.entity;

public class MyUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.ID
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.NAME
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.ADDRESS
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.AGE
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.TOKEN
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.AVATAR_URL
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYUSER.PASSWORD
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.ID
     *
     * @return the value of MYUSER.ID
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.ID
     *
     * @param id the value for MYUSER.ID
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.NAME
     *
     * @return the value of MYUSER.NAME
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.NAME
     *
     * @param name the value for MYUSER.NAME
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.ADDRESS
     *
     * @return the value of MYUSER.ADDRESS
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.ADDRESS
     *
     * @param address the value for MYUSER.ADDRESS
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.AGE
     *
     * @return the value of MYUSER.AGE
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.AGE
     *
     * @param age the value for MYUSER.AGE
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.TOKEN
     *
     * @return the value of MYUSER.TOKEN
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.TOKEN
     *
     * @param token the value for MYUSER.TOKEN
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.AVATAR_URL
     *
     * @return the value of MYUSER.AVATAR_URL
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.AVATAR_URL
     *
     * @param avatarUrl the value for MYUSER.AVATAR_URL
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MYUSER.PASSWORD
     *
     * @return the value of MYUSER.PASSWORD
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MYUSER.PASSWORD
     *
     * @param password the value for MYUSER.PASSWORD
     *
     * @mbg.generated Thu Sep 26 21:28:26 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}