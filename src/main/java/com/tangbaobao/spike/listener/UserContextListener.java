package com.tangbaobao.spike.listener;

import com.tangbaobao.spike.util.CookieUtil;
import com.tangbaobao.spike.util.UserContextHolder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tangxuejun
 * @version 2018/10/10 11:05 PM
 */
public class UserContextListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        UserContextHolder.clearUserContext();
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            UserContextHolder.setUserContext(CookieUtil.getCurrentUserFromCookie(request));
        }
    }
}
