//package com.cy.store.util;
//
//import org.springframework.util.DigestUtils;
//
///**
// * md5加密工具类
// */
//public class WebUtil {
//    /**
//     * 定义一个md5算法的加密
//     * @param password 原始密码
//     * @param salt 盐值
//     * @return 返回一个加密后的密码
//     */
//    public static String getMD5Password(String password,String salt){
//        /**
//         * 加密规则：
//         * 1.无视原密码的强度
//         * 2.盐值左右拼接
//         * 3.循环加密3次
//         */
//        for (int i=0;i<3;i++){
//            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
//        }
//        return password;
//    }
//
//}
