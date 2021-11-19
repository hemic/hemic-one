package com.hemic.one.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemic.common.model.AbstractAuditingEntity;
import com.hemic.one.constants.Constants;
import com.hemic.one.constants.State;
import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A user.
 *
 * @author Administrator
 */
@Entity
@Table(name = "one_user")
public class User extends AbstractAuditingEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -3213319314578336455L;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 64)
    @Column(name = "username", length = 64, nullable = false)
    private String username;

    @JsonIgnore
    @Size(min = 8, max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;


    @Size(max = 254)
    @Column(name = "full_name", length = 254)
    private String fullName;

    @Email
    @Size(min = 5, max = 254)
    @Column(name = "email", length = 254)
    private String email;


    @Column(name = "is_manager")
    private Boolean isManager;

    @Column(name = "title", length = 64)
    private String title;

    @Column(name = "mobile_number", length = 64)
    private String mobileNumber;

    @Column(name = "employee_number", length = 64)
    private String employeeNumber;

    @Column(name = "seq", length = 64)
    private String seq;

    @Column(name = "state", length = 64)
    private String state;

    @Column(name = "status", length = 64)
    private String status;

    @Column(name = "icon", length = 254)
    private String icon;


    public User() {

    }

    public User(String username, String fullName, String email, Boolean isManager, String title, String mobileNumber, String employeeNumber, String seq, String icon) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.isManager = isManager;
        this.title = title;
        this.mobileNumber = mobileNumber;
        this.employeeNumber = employeeNumber;
        this.seq = seq;
        this.icon = icon;
        this.state = State.ACTIVE.name();
    }

    public void changeBaseInfo(String fullName, String email, Boolean isManager, String title, String mobileNumber, String employeeNumber, String seq, String icon) {
        this.fullName = fullName;
        this.email = email;
        this.isManager = isManager;
        this.title = title;
        this.mobileNumber = mobileNumber;
        this.employeeNumber = employeeNumber;
        this.seq = seq;
        this.icon = icon;
    }

    public void generatorPassword(PasswordEncoder encoder, String password) {
        this.password = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
