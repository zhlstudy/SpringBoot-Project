package com.cy.store.mapper;


import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderMapperTests {
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(10);
        order.setRecvName("小王");
        order.setRecvPhone("133333");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(10);
        orderItem.setPid(10000001);
        orderItem.setTitle("高档铅笔");
        orderMapper.insertOrderItem(orderItem);
    }
//    @Test
//    public void chauid() {
//        System.out.println(orderMapper.queryOrderByUid(21));
//    }
    @Test
    public void chauid() {
        System.out.println(orderMapper.queryOrderVoByOid(8));
    }
}
