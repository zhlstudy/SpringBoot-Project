package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

//用来处理数据User模块的对应接口
public interface UserMapper {
    //插入用户的数据
    Integer insert(User user);

    //根据用户的名称查询用户数据
    User findByUsername(String username);

    //    根据uid更新用户的密码信息
    Integer updatePasswordByUid(Integer uid,String password,String modifiedUser,Date modifiedTime);

    //    根据用户uid查询当前的用户数据
    User findByUid(Integer uid);

//    根据uid更新用户的个人信息资料
    Integer UpdateInfoByUid(User user);

//    根据用户的uid来上传头像
    Integer updateAvatarByUid(@Param("uid")Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser")String modifiedUser,
                              @Param("modifiedTime")Date modifiedTime);
}
