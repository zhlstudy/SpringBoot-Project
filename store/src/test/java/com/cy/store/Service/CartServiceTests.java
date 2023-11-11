package com.cy.store.Service;

import com.cy.store.entity.Product;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        cartService.addToCart(14, 10000001, 5, "Tom");
    }
}
