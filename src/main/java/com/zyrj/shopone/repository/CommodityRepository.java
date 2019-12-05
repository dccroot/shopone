package com.zyrj.shopone.repository;

import com.zyrj.shopone.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity,Integer>,JpaSpecificationExecutor<Commodity> {

}
