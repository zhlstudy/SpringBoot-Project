package com.cy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;


//启动类的添加Mapper接口的包扫面
@MapperScan("com.cy.store.mapper")
@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

//	配置一个文件大小的默认值
	@Bean  //添加Spring容器中管理
	public MultipartConfigElement getMultipartConfigElement() {
		//1.创建一个配置的工厂类对象
		MultipartConfigFactory factory = new MultipartConfigFactory();

		//2.设置需要创建的对象的相关信息
		factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
		factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));

		//3.通过工厂类创建MultipartConfigElement对象
		return factory.createMultipartConfig();
	}

}
