package com.iswn.service.impl;

import com.iswn.bo.AddressBO;
import com.iswn.exception.http.RequestBadException;
import com.iswn.mapper.UserAddressMapper;
import com.iswn.pojo.UserAddress;
import com.iswn.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> queryAddressByUserId(String userId) {
        List<UserAddress> result = userAddressMapper.queryAddressByUserId(userId);

        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        // 1. 判断当前用户是否存在地址, 如果没有，则新增为'默认地址'
        List<UserAddress> addressList = this.queryAddressByUserId(addressBO.getUserId());

        Integer isDefault = 0;
        if (addressList == null || addressList.size() == 0) {
            isDefault = 1;
        }
        // 2. 保存地址到数据库
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, newAddress);

        // 用户id
        String userId = Sid.nextShort();
        newAddress.setId(userId);
        newAddress.setIsDefault(isDefault);
        newAddress.setUpdatedTime(new Date());
        newAddress.setCreatedTime(new Date());

        userAddressMapper.addUserAddress(newAddress);
    }

    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();

        if (StringUtils.isBlank(addressId)) {
            throw new RequestBadException("收货地址不能为空");
        }

        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);
        userAddress.setUpdatedTime(new Date());
        userAddress.setId(addressId);
        userAddressMapper.updateUserAddress(userAddress);
    }

    @Override
    public void deleteUserAddress(AddressBO addressBO) {
        userAddressMapper.deleteUserAddress(addressBO);
    }

    @Override
    public void setDefaultAddress(AddressBO addressBO) {
        List<UserAddress> result = userAddressMapper.queryAddressByUserId(addressBO.getUserId());
        /**
         * 把原先默认值改为0
         */
        for (UserAddress userAddress : result) {
            if (userAddress.getIsDefault().equals(1)) {
                UserAddress userAddress2 = new UserAddress();
                BeanUtils.copyProperties(addressBO, userAddress2);
                userAddress.setIsDefault(0);
                userAddressMapper.setDefaultAddress(userAddress);
            }
        }
        /**
         * 设置新的默认值
         */
        UserAddress userAddress3 = new UserAddress();

        userAddress3.setId(addressBO.getAddressId());
        userAddress3.setUserId(addressBO.getUserId());
        userAddress3.setIsDefault(1);

         userAddressMapper.setDefaultAddress(userAddress3);
    }
}
