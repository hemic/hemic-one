package com.hemic.common.config;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * @Author jor
 * @create 2021/11/4 14:31
 */
public interface PostEventListener extends PostUpdateEventListener, PostInsertEventListener, PostDeleteEventListener {

    @Override
    default void onPostUpdate(PostUpdateEvent event) {
        System.out.println(event);
    }

    @Override
    default void onPostInsert(PostInsertEvent event) {

    }

    @Override
    default void onPostDelete(PostDeleteEvent postDeleteEvent) {

    }

    @Override
    default boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

    @Override
    default boolean requiresPostCommitHandling(EntityPersister persister) {
        return PostUpdateEventListener.super.requiresPostCommitHandling(persister);
    }


}
