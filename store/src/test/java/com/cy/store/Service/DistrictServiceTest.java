package com.cy.store.Service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class DistrictServiceTest {
    @Autowired
    private IDistrictService districtService;


    @Test
    public void getByParent() {
        //86代表中国,所有的省父代码号都是86
        List<District> list = districtService.getByParent("86");
        for (District district : list) {
            System.err.println(district);
        }
    }

}
