package com.zyrj.shopone.controller;

import com.zyrj.shopone.entity.Address;
import com.zyrj.shopone.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(description = "地址")
@RequestMapping(value = "address")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;


    @ApiOperation(value = "增加用户地址信息")
    @PostMapping("/importUserExcel")
    public void addAddress(@RequestBody Address address){
        addressService.add(address);
    }

}
