package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


//控制层的基类：统一管理控制层的异常
public class BaseController {

//   @ExceptionHandler 表示捕获应用中指定的异常
    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> handleException(Throwable e) {
        ResponseResult<Void> response = null;

        if (e instanceof UsernameDuplicateException) {
//            response.setState(4000);
//            response.setMessage("用户名已经被占用");
            ResponseResult.getResponseResult(4000, "用户名被占用！");
        }else if (e instanceof UsernameNotFoundException) {
            ResponseResult.getResponseResult(4001, "登录的用户不存在！");
        }else if (e instanceof PasswordNotMatchException) {
            ResponseResult.getResponseResult(4002, "用户名或密码错误！");
        } else if (e instanceof InsertException) {
//            response.setState(5000);
//            response.setMessage("插入数据时产生未知的异常");
            ResponseResult.getResponseResult(5000, "插入数据时产生未知的错误！");
        }else if (e instanceof UpdateException) {
            ResponseResult.getResponseResult(5001, "用户更新数据时产生未知的错误！");
        }else if (e instanceof FileEmptyException) {
            ResponseResult.getResponseResult(6000, "上传文件不能为空！");
        }else if (e instanceof FileSizeException) {
            ResponseResult.getResponseResult(6001, "上传的文件的大小超出了限制值！");
        }else if (e instanceof FileTypeException) {
            ResponseResult.getResponseResult(6002, "上传的文件类型超出了限制！");
        }else if (e instanceof FileStateException) {
            ResponseResult.getResponseResult(6003, "上传的文件状态异常！");
        }else if (e instanceof FileUploadException) {
            ResponseResult.getResponseResult(6003, "上传文件时读写异常！");
        }else if (e instanceof AddressCountLimitException) {
            ResponseResult.getResponseResult(7000, "收货地址总数超出限制的异常(20条)！");
        }else if (e instanceof AddressNotFoundException) {
            ResponseResult.getResponseResult(7001,"用户的收货地址数据不存在的异常");
        } else if (e instanceof AccessDeniedException) {
            ResponseResult.getResponseResult(7002,"用户的收货地址数据非法访问的异常的异常");
        }else if (e instanceof DeleteArddessException) {
            ResponseResult.getResponseResult(7003,"用户的收货地址数据删除的异常的异常");
        }else if (e instanceof ProductNotFoundException) {
            ResponseResult.getResponseResult(7004,"访问的商品数据不存在的异常");
        }else if (e instanceof CartNotFoundException) {
            ResponseResult.getResponseResult(7005,"购物车表不存在该商品的异常");
        }



        return null;//响应异常信息到客户端
    }

    /**
     * 获取session对象中的uid  username
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    public final Integer getUidFromSession(HttpSession session) {
        //getAttribute返回的是Object对象,需要转换为字符串再转换为包装类
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    public final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}
