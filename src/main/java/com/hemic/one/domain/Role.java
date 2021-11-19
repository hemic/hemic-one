package com.hemic.one.domain;

import com.hemic.common.model.AbstractAuditingEntity;
import java.io.Serial;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author jor
 * @create 2021/11/19 14:47
 */
@Entity
@Table(name = "one_role")
public class Role extends AbstractAuditingEntity {

    @Serial
    private static final long serialVersionUID = -4278011666632056874L;

    private String name;


    public String getName() {
        return name;
    }


}
