package com.zyrj.shopone.entity;


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
    private Integer uId;
    @Excel(name = "用户账号")
    private String uPhone;
    @Excel(name = "用户密码")
    private String uPwd;
    @Excel(name = "验证码")
    private String uCode;

}
