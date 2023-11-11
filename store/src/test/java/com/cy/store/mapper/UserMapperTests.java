package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest   //表示是当前类为一个测试类
public class UserMapperTests {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Test //org.junit.jupiter.api
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println("rows"+rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("admin");
        System.out.println(user);
    }
    @Test
    public void updatePasswordByUid(){
        int row=userMapper.updatePasswordByUid(
                11,
                "321",
                "管理员",
                new Date());
        System.out.println("row"+row);
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(12));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(11);
        user.setUsername("小张");
        user.setPhone("13333688");
        user.setEmail("1454@qq.com");
        user.setGender(1);
        user.setModifiedUser("xiaozhang");
        user.setModifiedTime(new Date());

        userMapper.UpdateInfoByUid(user);
    }

    @Test
    public void updateAvatar(){
        Integer uid=12;
        String avatar="2222";
        String username="xiaozhang";
        Date date=new Date();

//        Integer uid,String avatar,String modifiedUser,Date modifiedTime
        System.out.println(userMapper.updateAvatarByUid(uid,avatar,username,date));
    }
}
