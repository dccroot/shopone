package com.zyrj.shopone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "user")
public class User implements Serializable {


    private static final long serialVersionUID = 3231360568889864170L;
    @Id
    @GeneratedValue
    @Excel(name = "用户编号")
    @JsonProperty("uId")
    private Integer uId;
    @Excel(name = "用户账号")
    @JsonProperty("uPhone")
    private String uPhone;
    @Excel(name = "用户密码")
    @JsonProperty("uPwd")
    private String uPwd;
    @Excel(name = "验证码")
    @JsonProperty("uCode")
    private String uCode;

//    @AllArgsConstructor   全参构造
//    @NoArgsConstructor   无参构造
//    @JsonProperty("uPhone")  Json属性
//    @Column(name = "u_phone")  数据库字段名

}
