package com.zyrj.shopone.controller;


import com.zyrj.shopone.config.MyTimerTask;
import com.zyrj.shopone.config.ScheduleExecutorTest;
import com.zyrj.shopone.entity.Commodity;
import com.zyrj.shopone.service.impl.CommodityServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Api(description = "商品")
@RequestMapping(value = "commodity")
@RestController
public class CommodityController {

    @Autowired
    private CommodityServiceImpl commodityService;

    @ApiOperation(value = "新增商品")
    @PostMapping("/add")
    public void add(@RequestBody Commodity commodity) {
        commodity = commodityService.save(commodity);
        Scanner sc = new Scanner(System.in);
        System.out.println("是否将商品进行保存，输入：y/n ");
        String judge = sc.next();

        if ("n".equals(judge.toLowerCase())){
            Timer timer=new Timer();
            MyTimerTask myTimerTask=new MyTimerTask();
            myTimerTask.setcId(commodity.getCId());
            timer.schedule(myTimerTask,1000*3);
            System.err.println("10秒钟后自动删除未保存商品");
        }
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/findCommodityList")
    public List<Commodity> findCommodityList() {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        ScheduleExecutorTest job1 = new ScheduleExecutorTest("job1");
        ScheduleExecutorTest job2 = new ScheduleExecutorTest("job2");
        //从现在开始delay毫秒之后，每隔period执行一次job1
        service.scheduleAtFixedRate(job1,1,1,TimeUnit.SECONDS);
        service.scheduleAtFixedRate(job2,2,1,TimeUnit.SECONDS);


        List<Commodity> commodities = commodityService.findAll();


        return commodities != null && commodities.size() > 0 ? commodities : null;
    }

    @ApiOperation(value = "特定商品")
    @PostMapping("/findById")
    public Commodity findById(Integer id) {

        Commodity commodity = commodityService.findById(id);

        return commodity != null ? commodity : null;
    }

    @ApiOperation(value = "修改指定商品")
    @PutMapping("/updateCommodity")
    public void updateCommodity(@RequestBody Commodity commodity) {
        commodityService.save(commodity);
    }

    @ApiOperation(value = "删除指定商品")
    @DeleteMapping("/deleteCommodity/{id}")
    public void deleteCommodity(@PathVariable("id") Integer id) {
        commodityService.deleteById(id);
    }


    @ApiOperation(value = "EasyPoi商品数据导出")
    @GetMapping("/exportCommodityExcel")
    public void exportCommodityExcel(HttpServletResponse response) throws Exception{
        List<Commodity> commodities = commodityService.findAll();
        ExportParams params = new ExportParams("商品信息","测试");
        Workbook workbook =  ExcelExportUtil.exportExcel(params,Commodity.class,commodities);
        response.setHeader("content","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xls");
        response.setCharacterEncoding("UTF-8");

        workbook.write(response.getOutputStream());
    }


    @ApiOperation(value = "EasyPoi商品数据导入")
    @PostMapping("/importCommodityExcel")
    public void importCommodityExcel(@RequestBody  MultipartFile file)throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //excel的数据
        List<Commodity> list = ExcelImportUtil.importExcel(
                file.getInputStream(),
                Commodity.class, params);

        commodityService.saveAll(list);
    }
}
