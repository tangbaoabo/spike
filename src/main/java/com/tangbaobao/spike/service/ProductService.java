package com.tangbaobao.spike.service;

import com.tangbaobao.spike.vo.ProductVO;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:30 PM
 */
public interface ProductService {
    /**
     * 查询商品列表信息
     *
     * @return
     */
    List<ProductVO> findList();

    /**
     * 根据商品Id查询商品详情
     *
     * @param id
     * @return
     */
    ProductVO getDetailById(Long id);


    /**
     * 减库存
     *
     * @param productDetail
     */
    void reduceStock(ProductVO productDetail);
}
