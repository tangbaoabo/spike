package com.tangbaobao.spike.controller;

import com.tangbaobao.spike.controller.resp.ResultResp;
import com.tangbaobao.spike.mq.MQSender;
import com.tangbaobao.spike.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangxuejun
 * @version 2018/10/10 1:27 PM
 */
@Controller
public class CommonController {
    @Autowired
    private MQSender mqSender;

    @GetMapping("/common/{path}")
    public String commonRequestResolver(@PathVariable String path) {
        return path;
    }

    @RequestMapping("/mq/test")
    @ResponseBody
    public ResultVO sendmq() {
        mqSender.send("4782384shfkds");
        return ResultResp.SUCCESS();
    }
    @RequestMapping("/mq/topic")
    @ResponseBody
    public ResultVO sendmqTopic() {
        mqSender.sendTopic("4782384shfkds");
        return ResultResp.SUCCESS();
    }
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public ResultVO sendFanout() {
        mqSender.sendFanout("4782384shfkds");
        return ResultResp.SUCCESS();
    }
    @RequestMapping("/mq/header")
    @ResponseBody
    public ResultVO sendHeader() {
        mqSender.sendHeader("4782384shfkds");
        return ResultResp.SUCCESS();
    }
}

