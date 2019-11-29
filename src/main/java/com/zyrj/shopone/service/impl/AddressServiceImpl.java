package com.zyrj.shopone.service.impl;

import com.zyrj.shopone.entity.Address;
import com.zyrj.shopone.repository.AddressRepository;
import com.zyrj.shopone.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void add(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> findByUId(Integer uId) {
        List<Predicate> predicates = new ArrayList<>();
        Specification<Address> specification = (root, query, criteriaBuilder) -> {
            if (0 != uId){
                predicates.add(criteriaBuilder.equal(root.get("uId"),uId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return addressRepository.findAll(specification);
    }

}
