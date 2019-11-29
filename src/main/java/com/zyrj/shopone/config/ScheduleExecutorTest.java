package com.zyrj.shopone.config;

public class ScheduleExecutorTest implements Runnable{


    private String jobName;

    public ScheduleExecutorTest(String jobName) {
        this.jobName = jobName;
    }


    @Override
    public void run() {
        System.out.println("executor: "+jobName+Thread.currentThread().getName());
    }
}
