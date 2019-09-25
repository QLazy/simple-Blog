package com.example.myBlog.entity;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer notifier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.RECEIVER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer receiver;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer outerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.TYPE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.STATUS
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.NOTIFIER_NAME
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private String notifierName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    private String outerTitle;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.ID
     *
     * @return the value of NOTIFICATION.ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.ID
     *
     * @param id the value for NOTIFICATION.ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.NOTIFIER
     *
     * @return the value of NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getNotifier() {
        return notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.NOTIFIER
     *
     * @param notifier the value for NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.RECEIVER
     *
     * @return the value of NOTIFICATION.RECEIVER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.RECEIVER
     *
     * @param receiver the value for NOTIFICATION.RECEIVER
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.OUTER_ID
     *
     * @return the value of NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getOuterId() {
        return outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.OUTER_ID
     *
     * @param outerId the value for NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.TYPE
     *
     * @return the value of NOTIFICATION.TYPE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.TYPE
     *
     * @param type the value for NOTIFICATION.TYPE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @return the value of NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @param gmtCreate the value for NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.STATUS
     *
     * @return the value of NOTIFICATION.STATUS
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.STATUS
     *
     * @param status the value for NOTIFICATION.STATUS
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.NOTIFIER_NAME
     *
     * @return the value of NOTIFICATION.NOTIFIER_NAME
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public String getNotifierName() {
        return notifierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.NOTIFIER_NAME
     *
     * @param notifierName the value for NOTIFICATION.NOTIFIER_NAME
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName == null ? null : notifierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @return the value of NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public String getOuterTitle() {
        return outerTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @param outerTitle the value for NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Wed Sep 25 13:50:03 CST 2019
     */
    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle == null ? null : outerTitle.trim();
    }
}