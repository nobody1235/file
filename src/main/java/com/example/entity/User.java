package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String userName;
    private String userSex;
    private String userPwd;
    private String realName;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserSex() {
        return userSex;
    }
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", userSex='" + userSex + '\'' + ", userPwd='" + userPwd + '\'' + ", realName='" + realName + '\'' + ", createTime=" + createTime + ", createUser='" + createUser + '\'' + ", updateTime=" + updateTime + ", updateUser='" + updateUser + '\'' + '}';
    }
}