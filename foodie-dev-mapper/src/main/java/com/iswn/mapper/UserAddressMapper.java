package com.iswn.mapper;

import com.iswn.bo.AddressBO;
import com.iswn.pojo.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressMapper {
    /**
     * 根据用户id查询收货地址
     * @param userId
     * @return
     */
    List<UserAddress> queryAddressByUserId(@Param("userId") String userId);

    /**
     * 添加用户收货地址
     * @param userAddress
     * @return
     */
    Integer addUserAddress(UserAddress userAddress);

    /**
     * 修改用户收货地址
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * 根据用户id和地址id删除收货地址
     * @param addressBO
     */
    void deleteUserAddress(AddressBO addressBO);

    /**
     * 设置默认收货地址
     * @param userAddress
     */
    void setDefaultAddress(UserAddress userAddress);
}