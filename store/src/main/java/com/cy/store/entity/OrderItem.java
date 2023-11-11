package com.cy.store.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 订单中的商品数据 */
@Data
@NoArgsConstructor //无参
@AllArgsConstructor  //全参
public class OrderItem extends BaseEntity {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
    /**
     * get,set
     * equals和hashCode
     * toString
     */
}

