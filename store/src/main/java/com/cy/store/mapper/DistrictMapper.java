package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;


public interface DistrictMapper {
    /**
     * 根据父代码号查询区域信息
     */
    List<District> findByParent(String parent);//查询的结果可能是多个,所以放在集合中

    String findNameByCode(String code);

}
