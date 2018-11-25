package com.tangbaobao.spike.dao;

import com.tangbaobao.spike.domain.Order;
import com.tangbaobao.spike.domain.SpikeOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author tangxuejun
 * @version 2018/10/15 4:08 PM
 */
@Component
public interface OrderDao {
    SpikeOrder getSpikeOrderByUserIdAndProductId(@Param("productId") Long productId, @Param("userId") Long userId);

    Long save(Order order);

    void saveSpikeOrder(SpikeOrder spikeOrder);
}
