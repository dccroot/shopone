package com.zyrj.shopone.util;

import com.zyrj.shopone.entity.Address;
import com.zyrj.shopone.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


// Specification<User> specification = (root, query, criteriaBuilder) -> {
//         List<Predicate> predicates = new ArrayList<>();
//         if (!StringUtils.isEmpty(phone)) {
//         predicates.add(criteriaBuilder.like(root.get("uPhone"), '%' + phone + '%'));
//         }
//         return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//         };



//大于      finalConditions = criteriaBuilder.and(finalConditions, criteriaBuilder.greaterThan(root.get("startDate"), new Date()));
//不等于    finalConditions = criteriaBuilder.and(finalConditions, criteriaBuilder.notEqual(root.get("status"), 1));


public class SpecificationUtil {
    public List<User> findUser(User user) {
        Specification<User> specification = new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();

                //查询条件示例
                //equal示例
                if (0 != user.getUId()) {
                    Predicate uIdPredicate = criteriaBuilder.equal(root.get("uId"), user.getUId());
                    predicatesList.add(uIdPredicate);
                }
                //like示例
                if (!StringUtils.isEmpty(user.getUPhone())) {
                    Predicate nickNamePredicate = criteriaBuilder.like(root.get("uPhone"), '%' + user.getUPhone() + '%');
                    predicatesList.add(nickNamePredicate);
                }
                //between示例
                if (0 != user.getUId()) {
                    Predicate birthdayPredicate = criteriaBuilder.between(root.get("uId"), user.getUId(), 100);
                    predicatesList.add(birthdayPredicate);
                }

                //关联表查询示例
                if (0 != user.getUId()) {
                    Join<User, Address> joinTeacher = root.join("address", JoinType.LEFT);
                    Predicate coursePredicate = criteriaBuilder.equal(joinTeacher.get("courseName"), user.getUId());
                    predicatesList.add(coursePredicate);
                }
                //Examination
                //复杂条件组合示例
                if (0 != user.getUId() && !StringUtils.isEmpty(user.getUPhone()) && !StringUtils.isEmpty(user.getUPwd()) && !StringUtils.isEmpty(user.getUCode())) {
                    Join<User, Integer> joinExam = root.join("exams", JoinType.LEFT);
                    Predicate predicateExamChinese = criteriaBuilder.ge(joinExam.get("uId"), user.getUId());
                    Predicate predicateExamMath = criteriaBuilder.ge(joinExam.get("uPhone"), Integer.parseInt(user.getUPhone()));
                    Predicate predicateExamEnglish = criteriaBuilder.ge(joinExam.get("uPwd"), Integer.parseInt(user.getUPwd()));
                    Predicate predicateExamPerformance = criteriaBuilder.ge(joinExam.get("uCode"), Integer.parseInt(user.getUPwd()));
                    //组合
                    Predicate predicateExam = criteriaBuilder.or(predicateExamChinese, predicateExamEnglish, predicateExamMath);
                    Predicate predicateExamAll = criteriaBuilder.and(predicateExamPerformance, predicateExam);
                    predicatesList.add(predicateExamAll);
                }

                //排序示例(先根据学号排序，后根据姓名排序)
                query.orderBy(criteriaBuilder.asc(root.get("studentNumber")), criteriaBuilder.asc(root.get("name")));

                //最终将查询条件拼好然后return
                Predicate[] predicates = new Predicate[predicatesList.size()];
                return criteriaBuilder.and(predicatesList.toArray(predicates));
            };
        };
        return null;
    }
}
