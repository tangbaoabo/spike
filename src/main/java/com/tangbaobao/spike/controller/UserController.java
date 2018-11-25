package com.tangbaobao.spike.controller;

import com.tangbaobao.spike.controller.resp.ResultResp;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.exception.SkipException;
import com.tangbaobao.spike.service.UserService;
import com.tangbaobao.spike.util.CookieUtil;
import com.tangbaobao.spike.util.MD5Util;
import com.tangbaobao.spike.util.UUIDUtil;
import com.tangbaobao.spike.vo.ResultVO;
import com.tangbaobao.spike.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.tangbaobao.spike.constant.CookieConsts.EXPIRE_TIME;
import static com.tangbaobao.spike.enums.ExceptionEnums.AUTH_FAIL;
import static com.tangbaobao.spike.enums.ExceptionEnums.USER_NOT_EXISTS;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:22 AM
 */
@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public UserController(UserService userService, RedisTemplate<String, Object> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/login")
    public ResultVO<Object> login(@Valid UserVO userVO, HttpServletResponse response) throws SkipException {
        log.info("phone={}", userVO.getPhone());
        //校验用户是否存在
        Optional<User> userOptional = userService.getUserByPhone(userVO.getPhone());
        User user = userOptional.orElseThrow(() -> new SkipException(USER_NOT_EXISTS));
        //校验密码
        String calPass = MD5Util.formPass2DBPass(userVO.getPassword(), user.getSalt());
        if (!calPass.equals(user.getPassword())) {
            throw new SkipException(AUTH_FAIL);
        }
        String token = UUIDUtil.generateUUID();
        //将用户信息和保存在缓存中
        redisTemplate.opsForValue().set(token, user, EXPIRE_TIME, TimeUnit.SECONDS);
        //放在cookie中
        CookieUtil.setCurrentUserToCookie(response, token, EXPIRE_TIME);

        return ResultResp.SUCCESS();
    }

    @RequestMapping("/info")
    public ResultVO info(Model model, User user) {
        return ResultResp.SUCCESS(user);
    }

}
