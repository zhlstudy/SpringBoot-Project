package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    //reg方法核心就是调用mapper层的方法,所以要声明UserMapper对象并加@Autowired注解
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        /**
         * 查询热销商品的前四名
         * @return 热销商品前四名的集合
         */
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setSellPoint(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;

    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }


}
