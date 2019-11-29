package com.zyrj.shopone.service.impl;

import com.zyrj.shopone.entity.Commodity;
import com.zyrj.shopone.repository.CommodityRepository;
import com.zyrj.shopone.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.saveAndFlush(commodity);
    }

    @Override
    public List<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public Commodity findById(Integer id) {
        return commodityRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public void saveAll(List list) {
        commodityRepository.saveAll(list);
    }
}
