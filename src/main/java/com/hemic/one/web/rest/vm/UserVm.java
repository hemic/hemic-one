package com.hemic.one.web.rest.vm;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author jor
 * @create 2021/11/9 15:03
 * <p>
 * A DTO representing a user . 用户对象的数据传输类
 * </p>
 */
public class UserVm implements Serializable {

    @Serial
    private static final long serialVersionUID = -4742448937348749246L;


    private String userName;

    private String fullName;

    private String title;

    private String email;

    private String mobileNumber;

    private String employeeNumber;

    private String seq;

    private String icon;

    private Boolean isManager;


    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
