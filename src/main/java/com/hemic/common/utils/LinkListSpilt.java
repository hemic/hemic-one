package com.hemic.common.utils;

import com.hemic.common.model.AbstractAuditingEntity;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author jor
 * @create 2021/12/2 14:37
 */
public class LinkListSpilt<T extends AbstractAuditingEntity> {


    private Collection<T> dbList;

    private Collection<T> newList;


    private Collection<T> createList = new ArrayList<>();

    private Collection<String> removeIds = new ArrayList<>();

    public LinkListSpilt(Collection<T> dbList, Collection<T> newList) {
        this.dbList = dbList;
        this.newList = newList;
        init();
    }

    public Collection<T> getCreateList() {
        return createList;
    }

    public Collection<String> getRemoveIds() {
        return removeIds;
    }

    private void init() {
        for (T t : newList) {
            if (!isContainId(t.getId(), dbList)) {
                createList.add(t);
            }
        }
        for (T t : dbList) {
            if (isContainId(t.getId(), newList)) {
                removeIds.add(t.getId());
            }
        }

    }

    private boolean isContainId(String id, Collection<T> list) {
        boolean flag = false;
        for (T t : list) {
            if (t.getId().equals(id)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
