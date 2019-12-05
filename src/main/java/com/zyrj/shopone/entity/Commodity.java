package com.zyrj.shopone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("cId")
    private Integer cId;

    @Excel(name = "商品名称")
    @JsonProperty("cName")
    private String cName;

    @Excel(name = "商品积分")
    @JsonProperty("cCost")
    private Integer cCost;

    @Excel(name = "商品价格")
    @JsonProperty("cPraise")
    private Integer cPraise;

}
