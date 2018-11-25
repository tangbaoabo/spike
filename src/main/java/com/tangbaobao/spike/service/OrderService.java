package com.tangbaobao.spike.service;

import com.tangbaobao.spike.domain.Order;
import com.tangbaobao.spike.domain.SpikeOrder;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.vo.ProductVO;

/**
 * @author tangxuejun
 * @version 2018/10/15 1:48 PM
 */
public interface OrderService {
    SpikeOrder getSpikeOrderByUserIdAndProductId(Long productId, Long userId);

    Order generateOrder(User user, ProductVO productDetail);
}
