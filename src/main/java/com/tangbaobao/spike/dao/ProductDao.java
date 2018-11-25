package com.tangbaobao.spike.dao;

import com.tangbaobao.spike.domain.Product;
import com.tangbaobao.spike.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2018/10/11 3:33 PM
 */
@Component
public interface ProductDao {
    List<ProductVO> findList();

    ProductVO getDetailById(@Param("id") Long id);

    void reduceStock(Product product);
}
