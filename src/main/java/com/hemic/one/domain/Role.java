package com.hemic.one.domain;

import com.hemic.common.model.AbstractAuditingEntity;
import java.io.Serial;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;

/**
 * @Author jor
 * @create 2021/11/19 14:47
 */
@Entity
@Table(name = "one_role")
public class Role extends AbstractAuditingEntity {

    @Serial
    private static final long serialVersionUID = -4278011666632056874L;

    @Column(name = "name", length = 254, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", orphanRemoval = true)
    @BatchSize(size = 20)
    Set<RolePermissionLink> permissionLinkSet = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public Set<RolePermissionLink> getPermissionLinkSet() {
        return permissionLinkSet;
    }

    public void addPermission(Permission permission) {
        Optional<RolePermissionLink> optional = getPermissionLinkSet().stream().filter(rolePermissionLink -> rolePermissionLink.getPermission().getId().equals(permission.getId())).findAny();
        if (optional.isEmpty()) {
            getPermissionLinkSet().add(new RolePermissionLink(this, permission));
        }
    }

    public void removePermission(Permission permission) {
        getPermissionLinkSet().removeIf(rolePermissionLink -> rolePermissionLink.getPermission().getId().equals(permission.getId()));
    }


}
