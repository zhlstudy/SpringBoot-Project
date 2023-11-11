package com.cy.store.controller;


import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public ResponseResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new  ResponseResult<>(ResponseResult.OK,"添加收获地址成功！你很优秀！！");
    }

    @RequestMapping({"","/"})
    public ResponseResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return ResponseResult.getResponseResult(data);
    }


    //RestFul风格的请求编写
    @RequestMapping("{aid}/set_default")
    public ResponseResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefault(aid, getUidFromSession(session), getUsernameFromSession(session));
        return new  ResponseResult<>(ResponseResult.OK,"设置默认收获地址成功！你很优秀！！");
    }

    @RequestMapping("{aid}/delete")
    public ResponseResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session) {
        addressService.delete(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new ResponseResult<>(ResponseResult.OK,"删除收获地址成功！你很优秀！！");
    }



}
