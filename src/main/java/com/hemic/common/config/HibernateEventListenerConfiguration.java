package com.hemic.common.config;

import javax.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

/**
 * @Author jor
 * @create 2021/11/1 9:23
 */
@Configuration
public class HibernateEventListenerConfiguration {


    public HibernateEventListenerConfiguration(EntityManagerFactory emf, PostEventListener listener) {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_COMMIT_UPDATE).appendListener(listener);

    }


}
