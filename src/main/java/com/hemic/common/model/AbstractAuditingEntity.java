package com.hemic.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by, last modified by attributes.
 *
 * @author jor
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditingEntity implements Serializable {


    private static final long serialVersionUID = 1L;

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


    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    @Column(name = "inner_version")
    private long innerVersion;

    @Column(name = "remark", length = 254)
    private String remark;

    @Column(name = "tenant_code", length = 254)
    private String tenantCode;

    public String getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public long getInnerVersion() {
        return innerVersion;
    }

    public String getRemark() {
        return remark;
    }

    public String getTenantCode() {
        return tenantCode;
    }
}
