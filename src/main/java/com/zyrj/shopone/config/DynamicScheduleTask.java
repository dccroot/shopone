package com.zyrj.shopone.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Configuration
public class DynamicScheduleTask implements SchedulingConfigurer{


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        //1.添加任务内容(Runnable)
        taskRegistrar.addTriggerTask( () ->
                       System.out.println("执行定时任务2: " + LocalDateTime.now().toLocalTime()),

                //2.设置执行周期(Trigger)
                triggerContext -> {

                    //2.1 执行周期
                    String cron = "*/10 * 8 * * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }

        );
    }

}
