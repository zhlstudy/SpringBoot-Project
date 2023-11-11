package com.cy.store.controller;


import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //Controller + ResponseBody
@RequestMapping("/products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

//    商品热销   首页展示
    @RequestMapping("hot_list")
    public ResponseResult<List<Product>> getHotList() {
        List<Product> products = productService.findHotList();
        return ResponseResult.getResponseResult(products);
    }
//商品详情展示
    @GetMapping("{id}/details")
    public ResponseResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return ResponseResult.getResponseResult(data);
    }
//加入购物车功能

}
