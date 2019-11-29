package com.zyrj.shopone.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "commodity")
public class Commodity implements Serializable{
    private static final long serialVersionUID = -144124732844137841L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "商品编号")
    private Integer cId;

    @Excel(name = "商品名称")
    private String cName;
    @Excel(name = "商品积分")
    private Integer cCost;
    @Excel(name = "商品价格")
    private Integer cPraise;

}
