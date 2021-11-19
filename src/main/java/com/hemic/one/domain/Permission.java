package com.hemic.one.domain;

import com.hemic.common.model.AbstractAuditingEntity;
import java.io.Serial;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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


    public String getAuthorizationUrl() {
        return authorizationUrl;
    }


}
