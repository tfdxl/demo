package com.example.entity;

/**
 * Created by tianfeng on 2017/5/30.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * @author tianfeng
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String password;

    private Date birthDay;

    private String sex;

    private String address;

    private String qq;

    private String email;

    private int age;


    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String userName, Date birthDay, String sex, String address, String password) {
        this.userName = userName;
        this.birthDay = birthDay;
        this.sex = sex;
        this.address = address;
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", birthDay=" + birthDay +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}