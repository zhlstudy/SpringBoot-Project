package com.cy.store.Service;

import com.cy.store.entity.Order;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;


    @Test
    public void create() {
        Integer[] cids = {2};
        Order order = orderService.create(14, cids, 14, "admin2");
        System.out.println(order);
    }

    @Test
    public void cha() {
        System.out.println(orderService.queryOrderVoByOid(4));
    }
}
