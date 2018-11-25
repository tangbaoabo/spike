package com.tangbaobao.spike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangxuejun
 * @version 2018/10/9 10:56 AM
 */
@Controller
@RequestMapping("/api/hello")
public class SimpleController {
    @GetMapping("/show")
    public String showDemo(ModelMap map) {
        map.put("username", "唐学俊");
        return "hello";
    }
}
