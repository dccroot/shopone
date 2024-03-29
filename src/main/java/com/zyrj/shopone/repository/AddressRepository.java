package com.zyrj.shopone.repository;

import com.zyrj.shopone.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>,JpaSpecificationExecutor<Address> {

}
