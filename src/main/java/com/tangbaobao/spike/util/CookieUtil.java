package com.tangbaobao.spike.util;

import com.tangbaobao.spike.domain.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

import static com.tangbaobao.spike.constant.CookieConsts.PATH;
import static com.tangbaobao.spike.constant.CookieConsts.TOKEN;

/**
 * @author tangxuejun
 * @version 2018/10/10 8:33 PM
 */
@Setter
@Component
public class CookieUtil {

    private final RedisTemplate<String, User> redisTemplate;

    private static CookieUtil cookieUtil;

    @Autowired
    public CookieUtil(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        cookieUtil = this;
    }

    public static void setCurrentUserToCookie(HttpServletResponse response, String token, int expire) {
        response.addCookie(getCookie(TOKEN, token, expire));
    }


    public static User getCurrentUserFromCookie(HttpServletRequest request) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(x -> x.getName().equals(TOKEN))
                .findFirst();
        return cookieUtil.redisTemplate.opsForValue().get(cookie.get().getValue());
    }

    public static User getCurrentUserFromRedis(String token) {
        return cookieUtil.redisTemplate.opsForValue().get(token);
    }

    private static Cookie getCookie(String key, String value, int expire) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expire);
        cookie.setPath(PATH);
        return cookie;
    }
}
