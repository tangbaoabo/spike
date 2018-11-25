package com.tangbaobao.spike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:23 PM
 */
@Getter
@Setter
@ToString
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Long deliveryAddrId;
    private String productName;
    private Integer productCount;
    private BigDecimal productPrice;
    private Short orderSource;
    private Short status;
    private LocalDateTime ctime;
    private LocalDateTime payTime;
}
