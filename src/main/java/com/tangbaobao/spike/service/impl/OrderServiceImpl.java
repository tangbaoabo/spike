package com.tangbaobao.spike.service.impl;

import com.tangbaobao.spike.dao.OrderDao;
import com.tangbaobao.spike.domain.Order;
import com.tangbaobao.spike.domain.SpikeOrder;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.service.OrderService;
import com.tangbaobao.spike.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author tangxuejun
 * @version 2018/10/15 1:49 PM
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public SpikeOrder getSpikeOrderByUserIdAndProductId(Long productId, Long userId) {
        return orderDao.getSpikeOrderByUserIdAndProductId(productId, userId);
    }

    @Override
    public Order generateOrder(User user, ProductVO productDetail) {
        log.info("user={},productDetail={}", user, productDetail);
        Order order = new Order();
        BeanUtils.copyProperties(productDetail, order, "id");
        order.setCtime(LocalDateTime.now());
        order.setDeliveryAddrId(0L);
        order.setOrderSource((short) 1);
        order.setStatus((short) 0);
        order.setUserId(user.getId());
        Long orderId = orderDao.save(order);

        SpikeOrder spikeOrder = new SpikeOrder();
        spikeOrder.setProductId(productDetail.getProductId());
        spikeOrder.setOrderId(orderId);
        spikeOrder.setUserId(user.getId());

        orderDao.saveSpikeOrder(spikeOrder);
        return order;
    }
}
