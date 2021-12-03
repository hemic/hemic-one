package com.hemic.one.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemic.common.model.AbstractAuditingEntity;
import com.hemic.one.constants.Constants;
import com.hemic.one.constants.State;
import java.io.Serial;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A user.
 *
 * @author Administrator
 */
@Entity
@Table(name = "one_user")
public class User extends AbstractAuditingEntity {


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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @BatchSize(size = 20)
    private Set<UserRoleLink> userRoleLinkSet = new HashSet<>();


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

    public void addRole(Role role) {
        Optional<UserRoleLink> optional = getUserRoleLinkSet().stream().filter(userRoleLink -> userRoleLink.getRole().getId().equals(role.getId())).findAny();
        if (optional.isEmpty()) {
            getUserRoleLinkSet().add(new UserRoleLink(this, role));
        }
    }

    public void removeRole(String roleId) {
        getUserRoleLinkSet().removeIf(userRoleLink -> userRoleLink.getRole().getId().equals(roleId));
    }

    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<Role>();
        for (UserRoleLink link : getUserRoleLinkSet()) {
            roles.add(link.getRole());
        }
        return roles;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getManager() {
        return isManager;
    }

    public String getTitle() {
        return title;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getSeq() {
        return seq;
    }

    public String getState() {
        return state;
    }

    public String getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public Set<UserRoleLink> getUserRoleLinkSet() {
        return userRoleLinkSet;
    }
}
