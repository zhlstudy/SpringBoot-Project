package com.cy.store.mapper;


import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTest {
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("210100");
        for (District district : list) {
            System.out.println(district);
        }
    }

    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
