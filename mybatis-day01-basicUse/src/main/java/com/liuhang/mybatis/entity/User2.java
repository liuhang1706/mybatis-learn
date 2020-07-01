package com.liuhang.mybatis.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 此时实体类属性与数据库表的字段已经不一致了
 *      1.在 SQL 语句中为所有列起别名，使别名与实体类属性名一致（执行效率相对高，开发效率低）
 *      2.使用 resultMap 完成结果集到实体类的映射（执行效率相对低，开发效率高）
 */
public class User2 implements Serializable {
    private Integer userId;
    private String userName;
    private Date userBirthday;
    private String userSex;
    private String userAddress;

    @Override
    public String toString() {
        return "User2{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userBirthday=" + userBirthday +
                ", userSex='" + userSex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
