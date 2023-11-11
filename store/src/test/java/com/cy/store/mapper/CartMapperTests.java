package com.cy.store.mapper;


import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.entity.VO.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTests {
    @Autowired(required = false)
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(10000001);
        cart.setNum(3);
        cart.setPrice(4L);//长整型
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "张三", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(11, 10000001);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(11);
        System.out.println(list);
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 8, 100};//可以写表中不存在的,无非就是查不到数据,并不会报错
        List<CartVO> list = cartMapper.findVOByCids(cids);
        for (CartVO item : list) {
            System.out.println(item);
        }
    }


}
