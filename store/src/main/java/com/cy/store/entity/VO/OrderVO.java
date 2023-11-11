package com.cy.store.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String zip;
    private String phone;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String address;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    private String image;
    private String title;
    private Long price;
    private Integer num;
}
