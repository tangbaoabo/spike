package com.tangbaobao.spike.util;

import com.tangbaobao.spike.domain.User;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:11 PM
 */
public class UserContextHolder {
    private static final ThreadLocal<User> USER_CONTEXT_HOLDER = new ThreadLocal<>();

    public static User getCurrentUser() {
        return USER_CONTEXT_HOLDER.get();
    }


    public static void setUserContext(User user) {
        if (user == null) {
            clearUserContext();
            return;
        }
        USER_CONTEXT_HOLDER.set(user);
    }
    public static void clearUserContext() {
        USER_CONTEXT_HOLDER.remove();
    }
}
