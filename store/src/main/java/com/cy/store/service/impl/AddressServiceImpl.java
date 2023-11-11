package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**新增收货地址的实现类*/
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

//    在新增收货地址的业务层需要对address进行封装,使其存有所有数据,然后将address传给持久层
    @Autowired(required = false)
    private IDistrictService districtService;

    //spring读取配置文件中数据:@Value("${user.address.max-count}")
    @Value("${user.address.max-count}")
    private Integer maxCount;


    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //调用统计收货地址数量的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//1表示默认收货地址,0反之
        address.setIsDefault(isDefault);

        /**
         * 对address对象中的数据进行补全:省市区的名字看前端代码发现前端传递过来的省市区的name分别为:
         * provinceCode,cityCode,areaCode,所以这里可以用address对象的get方法获取这三个的数据
         */
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //调用插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入用户的收货地址时产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        /**
         * 收货地址列表在前端只展示了四个数据,这里让其他值为空降低数据量
         * ProvinceName,CityName,AreaName,aid,tel(确认订单页展示展示用户地
         * 址时用到tel)在展示地址列表用不到,但是后面提交订单时的地址会用到,所以这里不设为null
         * */
        for (Address address : list) {
            //address.setAid(null);
            address.setUid(null);
            //address.setProvinceName(null);
            address.setProvinceCode(null);
            //address.setCityName(null);
            address.setCityCode(null);
            //address.setAreaName(null);
            address.setAreaCode(null);
            address.setZip(null);
            //address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }


    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        //1.检测是否有该条收货地址数据
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //2.检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        //3.先将所有的收货地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新收获地址数据时产生未知的异常");
        }
        //4.将用户选中的地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新收获地址数据时产生未知的异常");
        }
    }


    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        //1.
        if (result == null) {
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        //2.
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        //3.
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteArddessException("删除收货地址数据时产生未知的异常");
        }
        //4.如果删除的是非默认地址则不需要再做后面的任何操作,终止程序
        if (result.getIsDefault() == 0) {
            return;
        }
        //5.
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }
        //6.
        Address address = addressMapper.findLastModified(uid);
        //7.
        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }



    @Override
    public Address getByAid(Integer aid, Integer uid) {

        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("收货地址数据不存在的异常");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }

}
