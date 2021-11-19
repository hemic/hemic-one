package com.hemic.common.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @Author jor
 * @create 2021/11/19 15:26
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AbstractLinkEntity {

    @Id
    @GenericGenerator(
        name = "uuid2",
        strategy = "uuid2",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
        })
    @GeneratedValue(generator = "uuid2")
    private String id;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "tenant_code", length = 254)
    private String tenantCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }


    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getTenantCode() {
        return tenantCode;
    }
}
