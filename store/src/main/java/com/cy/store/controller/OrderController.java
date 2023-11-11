package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.entity.VO.OrderVO;
import com.cy.store.service.IOrderService;
import com.cy.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public ResponseResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.create(
                aid,
                cids,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return ResponseResult.getResponseResult(data);
    }

    @RequestMapping("/uid")
    public ResponseResult<List<Order>> queryOrderByUid(HttpSession session) {
        Integer uid=getUidFromSession(session);//获取Session中的uid
        List<Order> list=orderService.queryOrderByUid(uid);

        return ResponseResult.getResponseResult(list);
    }

    @RequestMapping("/queryOrderVo/{oid}")
    public ResponseResult<List<OrderVO>>  queryOrderVo(@PathVariable("oid") Integer oid){
        List<OrderVO> orderVos = orderService.queryOrderVoByOid(oid);
        return ResponseResult.getResponseResult(orderVos);
    }

}
