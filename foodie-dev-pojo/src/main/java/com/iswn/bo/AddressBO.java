package com.iswn.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBO {
    private String id;
    private String addressId;
    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
