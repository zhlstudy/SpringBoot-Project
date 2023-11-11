package com.cy.store.service;


import com.cy.store.entity.Address;

import java.util.List;

/**收货地址的业务层接口*/
public interface IAddressService {

    /**
     *这三个参数的由来:
     * 1.首先肯定要有address
     * 2.业务层需要根据uid查询该用户收货地址总数及新建地址时给字段uid赋值
     * 3.业务层在创建/修改收货地址时需要同时修改数据库中创建人/修改人的字段
     */
    void addNewAddress(Integer uid, String username, Address address);

     //通过uid来查找数据用户的收获地址
    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址数据为默认收货地址
     * @param aid 收货地址的id* @param uid 用户id* @param username 修改执行人
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除用户选中的收货地址数据
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void delete(Integer aid,Integer uid,String username);


    Address getByAid(Integer aid, Integer uid);


}
