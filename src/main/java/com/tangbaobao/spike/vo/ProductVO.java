package com.tangbaobao.spike.vo;

import com.tangbaobao.spike.domain.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tangxuejun
 * @version 2018/10/14 8:07 PM
 */
@Setter
@Getter
@ToString
public class ProductVO extends Product {
    private Long productId;
    private Integer stockCount;
    private BigDecimal spikePrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
