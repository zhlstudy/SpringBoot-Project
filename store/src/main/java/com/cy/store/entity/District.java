package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class District extends BaseEntity{
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
