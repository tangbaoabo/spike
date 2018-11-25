package com.tangbaobao.spike.service.impl;

import com.tangbaobao.spike.domain.Order;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.service.OrderService;
import com.tangbaobao.spike.service.ProductService;
import com.tangbaobao.spike.service.SpikeService;
import com.tangbaobao.spike.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tangxuejun
 * @version 2018/10/15 4:18 PM
 */
@Service
public class SpikeServiceImpl implements SpikeService {
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public SpikeServiceImpl(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Order spike(User user, ProductVO productDetail) {
        //减库存
        productService.reduceStock(productDetail);

        //生成订单
        return orderService.generateOrder(user, productDetail);
    }
}
