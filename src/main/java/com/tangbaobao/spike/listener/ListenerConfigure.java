package com.tangbaobao.spike.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;

/**
 * @author tangxuejun
 * @version 2018/10/10 11:24 PM
 */
//@Configuration
public class ListenerConfigure {
   // @Bean
    public ServletListenerRegistrationBean userContextListener() {
        ServletListenerRegistrationBean<UserContextListener> registration = new ServletListenerRegistrationBean<>();
        UserContextListener listener = new UserContextListener();
        registration.setListener(listener);
        registration.setOrder(1);
        return registration;
    }
}
