package com.zyrj.shopone.service.impl;


import com.zyrj.shopone.entity.User;
import com.zyrj.shopone.repository.UserRepository;
import com.zyrj.shopone.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void saveAll(List list) {
        userRepository.saveAll(list);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findById(Integer id) {

       Specification<User> specification = (root, query, criteriaBuilder) -> {

        List<Predicate> predicates = new ArrayList<>();
            if (0 != id){
                predicates.add(criteriaBuilder.equal(root.get("uId"),id));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> likeByPhone(String phone) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(phone)) {
                predicates.add(criteriaBuilder.like(root.get("uPhone"), '%' + phone + '%'));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> betweenById(Integer beginId, Integer endId) {

        Specification<User> specification = new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();

                //查询条件示例
                //between示例
                if (0 != beginId && 0 != endId && endId > beginId) {
                    predicatesList.add(criteriaBuilder.between(root.get("uId"),beginId,endId));
                }

                //最终将查询条件拼好然后return
                return criteriaBuilder.and(predicatesList.toArray(new Predicate[0]));
            }
        };
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> inById(Integer [] ids) {

        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (ids.length > 1){
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("uId"));
                for (Integer i:ids
                     ) {
                    in.value(i);
                }
                predicates.add(in);
            }
            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> findByUser(User user) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
          List<Predicate> predicates = new ArrayList<>();
          if (0 != user.getUId()){
              predicates.add(criteriaBuilder.equal(root.get("uId"),user.getUId()));
          }
          if (!StringUtils.isEmpty(user.getUPhone())){
              predicates.add(criteriaBuilder.equal(root.get("uPhone"),user.getUPhone()));
          }
          if (!StringUtils.isEmpty(user.getUPwd())){
                predicates.add(criteriaBuilder.equal(root.get("uPwd"),user.getUPwd()));
          }
          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }

    @Override
    public Page<User> findAllAndPage(Integer page, Integer size){
        List<Predicate> predicates = new ArrayList<>();
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,"uId");
        return userRepository.findAll(specification,pageable);
    }

    @Override
    public List<User> findAddressByPhone(String phone) {

        List<Predicate> predicates = new ArrayList<>();
        Specification<User> specification = (root, query, criteriaBuilder) -> {
          if (!StringUtils.isEmpty(phone)){
              predicates.add(criteriaBuilder.equal(root.get("uPhone"),phone));
          }
          return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return userRepository.findAll(specification);
    }

    @Override
    public List<User> findByPhone(String phone) {
        List<Predicate> predicates = new ArrayList<>();
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(phone)){
                predicates.add(criteriaBuilder.equal(root.get("uPhone"),phone));
            }
            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return userRepository.findAll(specification);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
}
