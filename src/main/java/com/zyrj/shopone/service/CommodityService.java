package com.zyrj.shopone.service;

import com.zyrj.shopone.entity.Commodity;

import java.util.List;


public interface CommodityService {

    Commodity save(Commodity commodity);

    List<Commodity> findAll();

    Commodity findById(Integer id);

    void deleteById(Integer id);

    void saveAll(List list);
}
