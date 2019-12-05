package com.zyrj.shopone.service.impl;

import com.zyrj.shopone.entity.Commodity;
import com.zyrj.shopone.entity.SelectCommodity;
import com.zyrj.shopone.repository.CommodityRepository;
import com.zyrj.shopone.service.CommodityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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

    @Override
    public List<Commodity> findByNameAndPraiseAndCost(SelectCommodity selectCommodity) {

        Specification<Commodity> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
          if (!StringUtils.isEmpty(selectCommodity.getCommodityName())){
              predicates.add(criteriaBuilder.like(root.get("cName"),'%'+selectCommodity.getCommodityName()+'%'));
          }
          if (null != selectCommodity.getCommodityPraiseBegin() && null != selectCommodity.getCommodityPraisend()  && selectCommodity.getCommodityPraisend() > selectCommodity.getCommodityPraiseBegin()){
                  predicates.add(criteriaBuilder.between(root.get("cPraise"),selectCommodity.getCommodityPraiseBegin(),selectCommodity.getCommodityPraisend()));
          }
            if (null != selectCommodity.getCommodityCostBegin()  && null != selectCommodity.getCommodityCostEnd() && selectCommodity.getCommodityCostEnd() > selectCommodity.getCommodityCostBegin()){
                predicates.add(criteriaBuilder.between(root.get("cCost"),selectCommodity.getCommodityCostBegin(),selectCommodity.getCommodityCostEnd()));
            }
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
        return commodityRepository.findAll(specification);
    }
}
