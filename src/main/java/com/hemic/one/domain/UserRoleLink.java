package com.hemic.one.domain;

import com.hemic.common.model.AbstractLinkEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Author jor
 * @create 2021/11/19 15:35
 */
@Entity
@Table(name = "one_user_role")
public class UserRoleLink extends AbstractLinkEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


    public UserRoleLink(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserRoleLink)) {
            return false;
        }

        UserRoleLink that = (UserRoleLink) o;

        return new EqualsBuilder().append(user.getId(), that.user.getId()).append(role.getId(), that.role.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(user.getId()).append(role.getId()).toHashCode();
    }
}
