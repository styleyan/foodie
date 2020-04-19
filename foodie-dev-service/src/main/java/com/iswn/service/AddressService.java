package com.iswn.service;

import com.iswn.bo.AddressBO;
import com.iswn.pojo.UserAddress;

import java.util.List;

public interface AddressService {

    /**
     * 根据用户的id查询用户的收货地址
     * @param userId
     * @return
     */
    List<UserAddress> queryAddressByUserId(String userId);

    /**
     * 添加用户收货地址
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改收货地址
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 删除用户收货地址
     * @param addressBO
     */
    void deleteUserAddress(AddressBO addressBO);

    /**
     * 设置默认收货地址
     */
    void setDefaultAddress(AddressBO addressBO);
}
