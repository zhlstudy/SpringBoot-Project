package com.cy.store.service;

import com.cy.store.entity.VO.CartVO;

import java.util.List;

public interface ICartService {

    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> getVOByUid(Integer uid);


    /**
     * 增加用户的购物车中某商品的数量
     * @param cid
     * @param uid
     * @param username
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid,Integer uid, String username);
    Integer reduceNum(Integer cid,Integer uid, String username);//减少数量


    List<CartVO> getVOByCids(Integer uid, Integer[] cids);//uid是为了判断数据归属是否正确


}
