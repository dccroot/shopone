package com.zyrj.shopone.config;

import com.zyrj.shopone.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RequestMapping(value = "commodity")
@RestController
public class MyTimerTask extends TimerTask{

    private Integer cId;

    @Autowired
    private CommodityServiceImpl commodityService;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    @Override
    public void run() {
        System.out.println(cId);
        commodityService.deleteById(cId);
   }

//    if ("n".equals(judge.toLowerCase())){
//        Timer timer=new Timer();
//        MyTimerTask myTimerTask=new MyTimerTask();
//        myTimerTask.setcId(commodity.getCId());
//        timer.schedule(myTimerTask,1000*3);
//        System.err.println("10秒钟后自动删除未保存商品");
//    }
}
