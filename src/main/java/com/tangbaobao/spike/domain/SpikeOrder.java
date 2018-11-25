package com.tangbaobao.spike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:27 PM
 */
@Getter
@Setter
@ToString
public class SpikeOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long productId;
}
