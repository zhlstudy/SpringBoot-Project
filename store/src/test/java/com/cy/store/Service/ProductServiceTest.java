package com.cy.store.Service;

import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private IProductService ProductService;

    @Test
    public void findHotList(){
        List<Product> list=ProductService.findHotList();
        for (Product product:list){
            System.out.println(product);
        }
    }
}
