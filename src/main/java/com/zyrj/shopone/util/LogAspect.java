package com.zyrj.shopone.util;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.zyrj.shopone.controller..*.*(..) )")
    private void pointCutMethod() {
    }

    @AfterThrowing("pointCutMethod()")
    public void doAfterThrowing(){
        log.info("程序异常，未正常运行！！！");
    }

    @AfterReturning("pointCutMethod()")
    public void doAfterReturning(){
        log.info("程序运行成功！！！");
    }


    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //Class.Method
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();


        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        Date d = new Date();


        log.info("用户IP： "+IpUtil.getIpAddr(request));
        log.info("通过: " + request.getMethod()+"请求 URL= "+request.getRequestURL());
        log.info("在： "+dateFormat.format(d));
        log.info("访问了: "+classType+" 类中的 "+ methodName+" 方法");
        log.info("传递的参数： ");
        for (Object o:joinPoint.getArgs()
             ) {
            log.info(""+o);
        }
    }

}
