package com.cy.store.controller;

import com.cy.store.entity.VO.CartVO;
import com.cy.store.service.ICartService;
import com.cy.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public ResponseResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
     cartService.addToCart(getUidFromSession(session), pid, amount, getUsernameFromSession(session));
        return new  ResponseResult<>(ResponseResult.OK,"加入购物车成功！");

    }

    @RequestMapping({"", "/"})
    public ResponseResult<List<CartVO>> getVOByUid(HttpSession session) {
        System.out.print(getUidFromSession(session));
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        System.out.print(data);
        return  ResponseResult.getResponseResult(data);
    }

    //增加数量！！！
    @RequestMapping("{cid}/num/add")
    public ResponseResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return  ResponseResult.getResponseResult(data);
    }
    //减少数量！！！
    @RequestMapping("{cid}/num/reduceNum")
    public ResponseResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.reduceNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return  ResponseResult.getResponseResult(data);
    }


    @RequestMapping("list")
    public ResponseResult<List<CartVO>> findVOByCids(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOByCids(getUidFromSession(session), cids);
        return ResponseResult.getResponseResult(data);
    }


}
