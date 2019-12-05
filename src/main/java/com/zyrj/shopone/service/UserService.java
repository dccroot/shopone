package com.zyrj.shopone.service;


import com.zyrj.shopone.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

   void saveAll(List list);

   List<User> findAll();

   List<User> findById(Integer id);

   List<User> likeByPhone(String phone);

   List<User> betweenById(Integer beginId,Integer endId);

   List<User> inById(Integer [] ids);

   List<User> findByUser(User user);

   Page<User> findAllAndPage(Integer page,Integer size);

   List<User> findAddressByPhone(String phone);

   List<User>  findByPhone(String phone);

   void add(User user);

}
