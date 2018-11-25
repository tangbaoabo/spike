package com.tangbaobao.spike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:20 PM
 */
@Getter
@Setter
@ToString
public class SpikeProduct {
    private Long id;
    private Long productId;
    private BigDecimal spikePrice;
    private Integer stockCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
