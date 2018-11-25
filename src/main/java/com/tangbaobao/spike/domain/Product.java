package com.tangbaobao.spike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:17 PM
 */
@Getter
@Setter
@ToString
public class Product {
    private Long id;
    private String productName;
    private String productTitle;
    private String productImg;
    private String productDetail;
    private BigDecimal productPrice;
    private Integer productStoke;
}
