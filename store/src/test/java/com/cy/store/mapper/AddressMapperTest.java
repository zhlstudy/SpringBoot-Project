package com.cy.store.mapper;


import com.cy.store.entity.Address;
import com.cy.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTest {
    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(11);
        address.setPhone("133336");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(11);
        System.out.println(count);
    }

    @Test
    public void findByUid () {
        List<Address> list = addressMapper.findByUid(14);
        System.out.println(list);
    }
    @Test
    public void findByAid() {
        System.err.println(addressMapper.findByAid(11));
    }

    @Test
    public void updateNonDefault() {
        System.out.println(addressMapper.updateNonDefault(11));//有几条数据影响行数就输出几
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(13,"明明",new Date());
    }


    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(1));
    }


}
