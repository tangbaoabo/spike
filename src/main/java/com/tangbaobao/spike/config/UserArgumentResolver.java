package com.tangbaobao.spike.config;

import com.tangbaobao.spike.constant.CookieConsts;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.tangbaobao.spike.constant.CookieConsts.TOKEN;

/**
 * @author tangxuejun
 * @version 2018/10/10 11:42 PM
 */
@Service
@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Optional<HttpServletRequest> request = Optional.ofNullable(nativeWebRequest.getNativeRequest(HttpServletRequest.class));

        Optional<HttpServletResponse> response = Optional.ofNullable(nativeWebRequest.getNativeResponse(HttpServletResponse.class));

        //从用户传来的参数获取token
        Optional<String> parameterToken = request.map(x -> x.getParameter(TOKEN));

        //从cookie中获取token
        Optional<String> cookieToken = getCookieValue(request, TOKEN);

        //优先从用户传来的token中获取，则从cookie中获取
        String token = cookieToken.orElse(parameterToken.orElse(null));

        //重新设置返回值
        CookieUtil.setCurrentUserToCookie(response.get(), token, CookieConsts.EXPIRE_TIME);
        //优先返回有值的
        return CookieUtil.getCurrentUserFromRedis(token);
    }

    /**
     * 找token
     *
     * @param request
     * @param token
     * @return
     */
    private Optional<String> getCookieValue(Optional<HttpServletRequest> request, String token) {
        log.info("request={},token={}", request, token);
        Optional<Stream<Cookie>> cookieStream = request.map(HttpServletRequest::getCookies)
                .map(Arrays::stream);
        if (!cookieStream.isPresent()) {
            return Optional.empty();
        } else {
            return cookieStream.get()
                    .filter(x -> x.getName().equals(token))
                    .map(Cookie::getName)
                    .findFirst();
        }
    }
}
