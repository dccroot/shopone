package com.zyrj.shopone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configuration
public class Scheduling {

//    秒 分钟 小时 日期 月份 星期 年 　符号 , - * /
//    @Scheduled(cron = "*/10 * 8 * * ?")
    //initialDelay 方法延迟执行时间  fixedDelay 每隔多久执行一次
    @Scheduled(initialDelay = 1000,fixedDelay = 100000000)
    public void nowData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        System.err.println(simpleDateFormat.format(new Date()));
    }
}
