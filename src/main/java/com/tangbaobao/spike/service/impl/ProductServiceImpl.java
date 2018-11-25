package com.tangbaobao.spike.service.impl;

import com.tangbaobao.spike.dao.ProductDao;
import com.tangbaobao.spike.domain.Product;
import com.tangbaobao.spike.service.ProductService;
import com.tangbaobao.spike.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:32 PM
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<ProductVO> findList() {
        List<ProductVO> productVOList;
        return (productVOList = productDao.findList()) == null ? Collections.emptyList() : productVOList;
    }

    @Override
    public ProductVO getDetailById(Long id) {
        return productDao.getDetailById(id);
    }

    @Override
    public void reduceStock(ProductVO productDetail) {
        Product product = new Product();
        product.setId(productDetail.getId());
        product.setProductStoke(productDetail.getProductStoke());
        productDao.reduceStock(product);
    }
}
