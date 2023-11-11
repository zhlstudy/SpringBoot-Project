package com.cy.store.Service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Product;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("175726");
        address.setName("男朋友");
        addressService.addNewAddress(11,"mxy",address);
    }


    @Test
    public void setDefault() {
        addressService.setDefault(2,11,"管理员");
    }

    @Test
    public void delete() {
        addressService.delete(2,11,"10.10删除");
    }

}
