package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired(required = false)
    private DataSource  dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void getConnection() throws SQLException {
        //HikariProxyConnection@356318598 wrapping com.mysql.cj.jdbc.ConnectionImpl@7e31062c
        //表示默认采用Hikari连接池技术
        System.out.println(dataSource.getConnection());
    }

}
