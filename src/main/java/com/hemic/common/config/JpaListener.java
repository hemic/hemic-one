package com.hemic.common.config;

import java.io.Serial;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author jor
 * @create 2021/11/1 9:11
 */

public class JpaListener implements PostEventListener {

    Logger logger = LoggerFactory.getLogger(JpaListener.class);
    @Serial
    private static final long serialVersionUID = 8515995503468871762L;

    @Override
    public void onPostUpdate(PostUpdateEvent event) {

        for (int i = 0; i < event.getState().length; i++) {
            // 更新前的值
            Object oldValue = event.getOldState()[i];
            // 更新后的新值
            Object newValue = event.getState()[i];
            //更新的属性名
            String propertyName = event.getPersister().getPropertyNames()[i];

            logger.info(oldValue.toString(), newValue, propertyName);
        }
    }


    @Override
    public void onPostInsert(PostInsertEvent event) {

    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {

    }
}

