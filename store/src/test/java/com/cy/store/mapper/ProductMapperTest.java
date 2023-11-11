package com.cy.store.mapper;


import com.cy.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTest {
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> products=productMapper.findHotList();
        for (Product product:products){
            System.out.println(product);
        }
    }

}
