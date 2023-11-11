package com.cy.store.mapper;


import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.entity.VO.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//创建订单-持久层
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入某一个订单中商品数据
     * @param orderItem 订单中商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    //根据订单号查询订单
    List<Order> queryOrderByUid(Integer uid);


    List<OrderVO> queryOrderVoByOid(Integer oid);
}
