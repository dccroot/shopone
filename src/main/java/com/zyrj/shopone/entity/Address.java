package com.zyrj.shopone.entity;



import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "address")
public class Address implements Serializable{

    private static final long serialVersionUID = 3476670412812535773L;


    @Id
    @GeneratedValue
    @Excel(name = "地址编号")
    private Integer aId;

    @Excel(name = "用户编号")
    private Integer uId;

    @Excel(name = "详细地址")
    private String address;

    @Excel(name = "收件人")
    private String aName;

    @Excel(name = "收件人电话")
    private String aPhone;

}
