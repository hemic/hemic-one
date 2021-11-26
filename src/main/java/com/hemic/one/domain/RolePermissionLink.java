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
 * @create 2021/11/19 16:14
 */
@Entity
@Table(name = "one_role_permission")
public class RolePermissionLink extends AbstractLinkEntity {

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;


    public RolePermissionLink(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    public RolePermissionLink() {
    }

    public Role getRole() {
        return role;
    }

    public Permission getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof RolePermissionLink)) {
            return false;
        }

        RolePermissionLink that = (RolePermissionLink) o;

        return new EqualsBuilder().append(role.getId(), that.role.getId()).append(permission.getId(), that.permission.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(role.getId()).append(permission.getId()).toHashCode();
    }
}
