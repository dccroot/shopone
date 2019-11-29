package com.zyrj.shopone.service;

import com.zyrj.shopone.entity.Address;

import java.util.List;

public interface AddressService {

    void add(Address address);

    List<Address> findByUId(Integer uId);

}
