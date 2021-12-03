package com.hemic.one.domain;

import com.hemic.common.model.AbstractAuditingEntity;
import java.io.Serial;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Author jor
 * @create 2021/11/19 14:53
 */
@Entity
@Table(name = "one_permission")
public class Permission extends AbstractAuditingEntity {

    @Serial
    private static final long serialVersionUID = 2730784968140896189L;

    @Column(name = "authorization_url", length = 254)
    private String authorizationUrl;

    public Permission() {
    }

    public Permission(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Permission)) {
            return false;
        }

        Permission that = (Permission) o;

        return new EqualsBuilder().append(getAuthorizationUrl(), that.getAuthorizationUrl()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getAuthorizationUrl()).toHashCode();
    }
}
