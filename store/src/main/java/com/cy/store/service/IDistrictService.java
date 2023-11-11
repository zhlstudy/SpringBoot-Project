package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据父代码号来查询区域信息(省或市或区)
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
