//package com.henu.seafile.microservice.file.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.io.Serializable;
//
///**
// * @Author Pangpd
// * @Description
// * @Date 2019/7/11 11:46
// */
//
//@Entity
//@Table(name = "EmailUser")
//public class AccountEntiy implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private int id;
//    @Column(name = "email")
//    private String account;
//    @Column(name = "passwd")
//    private String password;
//    @Column(name = "is_staff")
//    private int isStaff;
//    @Column(name = "is_active")
//    private int isActive;
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getIsStaff() {
//        return isStaff;
//    }
//
//    public void setIsStaff(int isStaff) {
//        this.isStaff = isStaff;
//    }
//
//    public int getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(int isActive) {
//        this.isActive = isActive;
//    }
//}
