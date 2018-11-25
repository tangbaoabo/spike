package com.tangbaobao.spike.controller;

import com.tangbaobao.spike.controller.resp.ResultResp;
import com.tangbaobao.spike.domain.SpikeOrder;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.mq.MQSender;
import com.tangbaobao.spike.mq.SpikeMessage;
import com.tangbaobao.spike.service.OrderService;
import com.tangbaobao.spike.service.ProductService;
import com.tangbaobao.spike.service.SpikeService;
import com.tangbaobao.spike.vo.ProductVO;
import com.tangbaobao.spike.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.tangbaobao.spike.enums.ExceptionEnums.SPIKE_PRODUCT_REPEAT;

/**
 * @author tangxuejun
 * @version 2018/10/15 12:33 PM
 * 秒杀思路:
 * 1：查询库存
 * 2：开始秒杀
 * 3：减库存，写入订单
 */
@Controller
@RequestMapping("/spike")
@Slf4j
public class SpikeController implements InitializingBean {
    private ProductService productService;
    private OrderService orderService;
    private SpikeService spikeService;
    private RedisTemplate<String, Object> redisTemplate;
    private final MQSender mqSender;

    @Autowired
    public SpikeController(ProductService productService, OrderService orderService, SpikeService spikeService, RedisTemplate<String, Object> redisTemplate, MQSender mqSender) {
        this.productService = productService;
        this.orderService = orderService;
        this.spikeService = spikeService;
        this.redisTemplate = redisTemplate;
        this.mqSender = mqSender;
    }

    /**
     * 系统初始化的时候调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化的时候将库存数量放入redis中
        List<ProductVO> list = productService.findList();
        list.forEach(product -> redisTemplate.opsForValue().set(String.valueOf(product.getId()), product.getStockCount()));
    }

    //    @PutMapping("/spiking/")
//    public String spiking(@RequestParam("productId") Long productId,
//                          Model model, User user) {
//        log.info("productId={}", productId);
//        ProductVO productDetail = productService.getDetailById(productId);
//        Integer stockCount = productDetail.getStockCount();
//        if (stockCount <= 0) {
//            model.addAttribute("errMsg", SPIKE_PRODUCT_OVER.getMsg());
//            return "spike_fail";
//        }
//        //判断秒杀到了吗？
//        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdAndProductId(productId, user.getId());
//        if (spikeOrder != null) {
//            model.addAttribute("errMsg", SPIKE_PRODUCT_REPEAT.getMsg());
//            return "spike_fail";
//        }
//        //1.减库存
//        Order order = spikeService.spike(user, productDetail);
//        model.addAttribute("order", order);
//        model.addAttribute("product", productDetail);//2.下单
//        return "order_detail";
//    }
//
    @PutMapping("/spiking/")
    public ResultVO<Object> spiking(@RequestParam("productId") Long productId,
                                    Model model, User user) {
        model.addAttribute("user", user);
        //预减库存
        Long stock = redisTemplate.opsForValue().increment(String.valueOf(productId), -1);
        if (stock < 0) {
            return ResultResp.ERROR("秒杀结束");
        }
        //判断秒杀到了吗？
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdAndProductId(productId, user.getId());
        if (spikeOrder != null) {
            return ResultResp.ERROR(SPIKE_PRODUCT_REPEAT.getMsg());
        }
        //入队
        SpikeMessage skipeMessage = new SpikeMessage(user, productId);

        mqSender.sendSpikeMessage(skipeMessage);
        return ResultResp.SUCCESS("排队中");

    }


}
