package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    //reg方法核心就是调用mapper层的方法,所以要声明UserMapper对象并加@Autowired注解
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //1.通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用mapper的findByUsername(username)判断用户是否被注册过了
        User result = userMapper.findByUsername(username);
        //判断结果集是否为null,不为null的话则需抛出用户名被占用的异常
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicateException("当前用户名已经被占用");
        }


        //2.补全数据:四个日志字段信息
        Date date = new Date();//java.util.Date  获取当前系统时间
        //1.随机生成一个盐值(大写的随机字符串)
        String salt = UUID.randomUUID().toString().toUpperCase();
        /**
         * 密码加密处理作用:
         * 1.后端不再能直接看到用户的密码2.忽略了密码原来的强度,提升了数据安全性
         * 密码加密处理的实现:定义   下面的getMd5Password方法
         *///给当前用户设置经过加密后的密码
        String newMD5Password=getMd5Password(user.getPassword(),salt);
        user.setPassword(newMD5Password);


        //补全数据:is_delete设置为0
        //3.将盐值保存到数据库
        user.setSalt(salt);
        user.setIsDelete(0);//0 表示未删除
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //3.执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if (rows !=1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
//      根据用户名称来查询用户的数据是否存在,不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("用户数据不存在的异常");
        }
        /**
         * 检测用户的密码是否匹配:
         * 1.先获取数据库中加密之后的密码
         * 2.和用户传递过来的密码进行比较  2.1先获取盐值  2.2将获取的用户密码按照相同的md5算法加密
         */
        String newMd5Password = getMd5Password(password, result.getSalt());

        if (!result.getPassword().equals(newMd5Password)) {
            throw new PasswordNotMatchException("用户密码不匹配错误");
        }
//        传递给前端的数据：uid username avatar
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;//返回封装的User对象给前端
    }

    //定义一个用于加密用户的密码的方法
    private String getMd5Password(String password,String salt){
        //pwd-加密  salt 盐值
        //str + pwd + str - 加密
        //salt + ped + salt - 加密  toUpperCase() 转换为大写
        for (int i=0;i<3;i++){
            password =DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }





    @Override
    public void changePasswordByUid(Integer uid, String modifiedUser, String oldPassword, String newPassword) {
//        根据uid查询用户的数据
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

//       检验用户输入前端过来的原始密码是否与数据库中一致
        String salt=result.getSalt();//先获取salt 盐值
        String oldMd5Password=getMd5Password(oldPassword,salt);
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("用户原始密码错误");
        }

//       当前用户是否被标记为删除状态（数据库中还存在记录）
        if (result.getIsDelete().equals(1)){
            throw new UsernameNotFoundException("用户数据不存在！");
        }

        //将新的密码加密后设置到数据库中(只要曾经注册过就用以前的盐值)
        String newMd5Password = getMd5Password(newPassword, salt);
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, modifiedUser, new Date());

        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常!");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        //查询用户是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {//没找到   已删除
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //需要构建一个User对象！可以直接返回result给控制层,但是太臃肿了
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setAvatar(result.getAvatar());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String name, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(name);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.UpdateInfoByUid(user);
        if (rows !=1) {
            throw new UpdateException("用户个人更新数据时产生异常");
        }
    }

//    头像上传
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在的异常！");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!=1) {
            throw new UpdateException("更新用户头像时产生未知异常！");
        }
    }


}
