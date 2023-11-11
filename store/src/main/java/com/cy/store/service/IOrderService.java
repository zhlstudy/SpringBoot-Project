package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.entity.VO.OrderVO;

import java.util.List;


public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);

    //根据订单号查询订单
    List<Order> queryOrderByUid(Integer uid);

    List<OrderVO> queryOrderVoByOid(Integer oid);
}

