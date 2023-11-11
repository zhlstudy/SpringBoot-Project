package com.cy.store.Service;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        User user = new User();
        user.setUsername("张三2");
        user.setPassword("123456");

        userService.reg(user);
    }

    @Test
    public void login() {
        //因为login方法可能抛出异常,所以应该捕获异常,但是测试时没必要写那么严谨
        User user = userService.login("admin1", "admin1");
        System.out.println(user);
    }

    @Test
    public void changePasswordByUid() {
        userService.changePasswordByUid(12,"管理员","654321","123456");
    }

    @Test
    public void getByUid() {
        //err是为了让输出信息为红色
        System.err.println(userService.getByUid(11).getUsername());
    }

    @Test
    public void changeInfo() {
        User user = new User();
        //这四个属性值在真实开发中都是在控制层就已经自动封装在User对象中了
        //而uid需要由控制层传给业务层并在业务层封装到user中
        user.setPhone("123456789");
        user.setEmail("123@qq.com");
        user.setGender(1);
        userService.changeInfo(11,"666",user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(11,"/upload/6A5FF7A4-DF53-4F12-9508-64CA9D5B80DB.png","mmm");
    }


}
