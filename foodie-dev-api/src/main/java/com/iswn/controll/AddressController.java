package com.iswn.controll;

import com.iswn.bo.AddressBO;
import com.iswn.exception.http.RequestBadException;
import com.iswn.pojo.UserAddress;
import com.iswn.pojo.Users;
import com.iswn.service.AddressService;
import com.iswn.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地址相关控制器
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {
    final static Logger logger = LoggerFactory.getLogger(AddressController.class);
    /**
     * 用户在确认订单，可以针对收货地址做如下操作:
     * 1. 查询用户的所有收货地址列表
     * 2. 新增收货地址
     * 3. 删除收货地址
     * 4. 修改收货地址
     * 5. 设置默认地址
     */

    @Autowired
    private AddressService addressService;

    /**
     * 根据用户id 查询收货地址列表
     * @return
     */
    @GetMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");

        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }
        List<UserAddress> adds = addressService.queryAddressByUserId(users.getId());
        return JsonResult.success(adds);
    }

    /**
     * 根据用户id 添加收货地址列表
     * @param addressBO
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(HttpServletRequest httpServletRequest, @RequestBody AddressBO addressBO) {
        Users users = (Users)httpServletRequest.getAttribute("user");

        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }
        addressBO.setUserId(users.getId());
        addressService.addNewUserAddress(addressBO);

        return JsonResult.success();
    }

    /**
     * 根据用户id 查询收货地址列表
     * @param addressBO
     * @return
     */
    @PostMapping("/update")
    public JsonResult update(@RequestBody AddressBO addressBO) {
        if (addressBO == null) {
            throw new RequestBadException("地址不能为空");
        }
        /**
         * TODO: 缺少判断对参数的校验
         */
        addressService.updateUserAddress(addressBO);

        return JsonResult.success();
    }

    /**
     * 根据用户id 查询收货地址列表
     * @param addressId
     * @return
     */
    @PostMapping("/delete/{addressId}")
    public JsonResult delete(HttpServletRequest httpServletRequest, @PathVariable("addressId") String addressId) {
        if (StringUtils.isBlank(addressId)) {
            throw new RequestBadException("地址不能为空");
        }
        Users users = (Users)httpServletRequest.getAttribute("user");
        String userId = users.getId();
        if (StringUtils.isBlank(userId)) {
            throw new RequestBadException("用户id不能为空");
        }
        AddressBO addressBO = new AddressBO();
        addressBO.setAddressId(addressId);
        addressBO.setUserId(userId);
        addressService.deleteUserAddress(addressBO);
        return JsonResult.success();
    }

    /**
     * 根据用户id 设置默认收货地址
     * @param addressId
     * @return
     */
    @PostMapping("/setDefault/{addressId}")
    public JsonResult setDefault(HttpServletRequest httpServletRequest, @PathVariable("addressId") String addressId) {
        if (StringUtils.isBlank(addressId)) {
            throw new RequestBadException("地址不能为空");
        }
        Users users = (Users)httpServletRequest.getAttribute("user");
        String userId = users.getId();

        if (StringUtils.isBlank(userId)) {
            throw new RequestBadException("用户id不能为空");
        }
        AddressBO addressBO = new AddressBO();
        addressBO.setAddressId(addressId);
        addressBO.setUserId(userId);
        addressService.setDefaultAddress(addressBO);
        return JsonResult.success();
    }
}
