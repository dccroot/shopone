package com.zyrj.shopone.service;

import com.zyrj.shopone.entity.Commodity;
import com.zyrj.shopone.entity.SelectCommodity;

import java.util.List;
import java.util.Map;


public interface CommodityService {

    Commodity save(Commodity commodity);

    List<Commodity> findAll();

    Commodity findById(Integer id);

    void deleteById(Integer id);

    void saveAll(List list);

    List<Commodity> findByNameAndPraiseAndCost(SelectCommodity selectCommodity);
}
