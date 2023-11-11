package com.cy.store.controller;


import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.ResponseResult;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController  //Controller + ResponseBody
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    //继承BaseController后改良
    @RequestMapping("reg")
    public ResponseResult<Void> reg(User user) {
        userService.reg(user);
        return ResponseResult.getResponseResult("注册成功！你很优秀！");//如果没有产生异常，响应用户200（成功）
    }


    @RequestMapping("login")
    public ResponseResult<User> login(String username, String password,HttpSession session,String code){
        //将存储在session的kaptcha所生成的验证码取出
        String validCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否一致
        if (!validCode.equals(code)){
            throw new ValidCodeNotMatchException("验证码错误,请重试！");
        }


        User user = userService.login(username, password);
//        user信息记录下来（Session - 绑定uid，avatar）  Session的特点：每个程序都有一个唯一的Session对象，只要服务器不关Session就一直存在

        //向session对象中完成数据的绑定(这个session是全局的,项目的任何位置都可以访问)
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());

//        //测试能否正常获取session中存储的数据
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameFromSession(session));

        return ResponseResult.getResponseResult(user);
    }

    @RequestMapping("change_Password")
    public ResponseResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);//获取Session中的uid
        String username = getUsernameFromSession(session);
        userService.changePasswordByUid(uid,username,oldPassword,newPassword);

        return new  ResponseResult<>(ResponseResult.OK,"优秀的人！您修改密码成功！");
    }

    @RequestMapping("get_by_uid")
    public ResponseResult<User> getByUid(HttpSession session) {
        Integer uid=getUidFromSession(session);//获取Session中的uid
        User result = userService.getByUid(uid);
        System.err.print(result);
        return ResponseResult.getResponseResult(result);
    }

    @RequestMapping("change_info")
    public ResponseResult<Void> changeInfo(User user,HttpSession session) {
        //user对象中有四部分的数据:username,phone,email,gender
        //控制层给业务层传递uid,并在业务层通过user.setUid(uid);将uid封装到user中
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return ResponseResult.getResponseResult("恭喜你修改成功！！！");
    }



//----------------------------------------头像处理
//    头像的上传大小
    public static final int AVATAR_MAX_SIZE=10*1024*1024;
//    存储上传头像类型：png、jpeg、jpg、、、
    public static final List<String> AVATAR_TYPES=new ArrayList<>();
//    初始化可以上传的图片类型
    static {
    AVATAR_TYPES.add("image/jpeg");//响应前端：printWriter.setContentType("text/html")
    AVATAR_TYPES.add("image/png");
    AVATAR_TYPES.add("image/bmg");
    AVATAR_TYPES.add("image/jpg");
    AVATAR_TYPES.add("image/gif");
//    ....
    }

//                                             @RequestParam("file") 前端访问参数保持一致
    @RequestMapping("/change_avatar")
    public ResponseResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {

        //在保存文件之前对文件做检查
        //判断文件是否为null
        if (file.isEmpty()) {
            throw new FileEmptyException("上传的头像文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE) {
            throw new FileSizeException("上传的头像文件超出限制");
        }
        //判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        //如果集合包含某个元素则返回值为true
        if (!AVATAR_TYPES.contains(contentType)) {
            throw new FileTypeException("上传的头像文件类型不支持");
        }

        //获取上传文件的原始名字
        String originalFilename = file.getOriginalFilename();

        //获取文件的后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用时间戳为文件定义新的名字
        String uuidName = UUID.randomUUID().toString();

        //定义文件最终的名字
        String fileName = uuidName + suffix;
        System.err.println(fileName);

        //获取项目在服务器上的真实位置并拼凑文件最终的保存位置
        //本地的写法
        String realPath = "D:\\IDEA\\Project1\\store\\src\\main\\resources\\static\\images\\upload\\"+fileName;
        //虚拟创建目标文件
        File destFile = new File(realPath);
        if (!destFile.exists()){
            //代表文件的目录不存在，进行创建
            destFile.mkdirs();
        }

        //选择第一种方法，直接写入目标位置
        try {
            file.transferTo(destFile);
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常，写入失败");
        }catch (IOException e) {
            throw new FileUploadException("服务器或数据库写入文件失败");
        }
//      上传的文件保存地址
        Integer uid = getUidFromSession(session);
        System.err.print(uid);
        String username = getUsernameFromSession(session);
        String avatar ="/upload/"+fileName;
        userService.changeAvatar(uid,avatar,username);
        return ResponseResult.getResponseResult("上传头像成功！",avatar);
    }




////    定义注册义务的方法  刚开始的样子还没有统一处理的样子 没有继承BaseController
//    @RequestMapping("reg")
//    public ResponseResult<Void> reg(User user) {
//        //创建响应结果对象即ResponseResult对象
//        ResponseResult<Void> response = new ResponseResult<>();
//        try {
//            //调用userService的reg方法时可能出现异常,所以需要捕获异常
//            userService.reg(user);
//            response.setState(200);
//            response.setMessage("用户注册成功");
//        } catch (UsernameDuplicateException e) {
//            response.setState(4000);
//            response.setMessage("用户名已经被占用");
//        } catch (InsertException e) {
//            response.setState(5000);
//            response.setMessage("注册时产生未知的异常");
//        }
//        return response;
//    }


}





