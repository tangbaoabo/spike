package com.tangbaobao.spike.service;

import com.tangbaobao.spike.domain.Order;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.vo.ProductVO;

/**
 * @author tangxuejun
 * @version 2018/10/15 4:17 PM
 */
public interface SpikeService {

    Order spike(User user, ProductVO productDetail);
}
