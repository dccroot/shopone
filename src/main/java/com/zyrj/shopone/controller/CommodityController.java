package com.zyrj.shopone.controller;


import com.zyrj.shopone.entity.Commodity;
import com.zyrj.shopone.entity.SelectCommodity;
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


@Api(description = "商品")
@RequestMapping(value = "commodity")
@RestController
@CrossOrigin
public class CommodityController {

    @Autowired
    private CommodityServiceImpl commodityService;

    @ApiOperation(value = "新增商品")
    @PostMapping("/add")
    public void add(@RequestBody Commodity commodity) {
        commodity = commodityService.save(commodity);
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/findCommodityList")
    public List<Commodity> findCommodityList() {

//        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
//        ScheduleExecutorTest job1 = new ScheduleExecutorTest("job1");
//        ScheduleExecutorTest job2 = new ScheduleExecutorTest("job2");
//        //从现在开始delay毫秒之后，每隔period执行一次job1
//        service.scheduleAtFixedRate(job1,1,1,TimeUnit.SECONDS);
//        service.scheduleAtFixedRate(job2,2,1,TimeUnit.SECONDS);


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
    @DeleteMapping("/deleteCommodity/{cId}")
    public void deleteCommodity(@PathVariable Integer cId) {
        commodityService.deleteById(cId);
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

    @ApiOperation(value = "根据搜索框信息进行复杂查询")
    @PostMapping("/findByNameAndPraiseAndCost")
    public List<Commodity> findByNameAndPraiseAndCost(@RequestBody SelectCommodity selectCommodity) {
            return commodityService.findByNameAndPraiseAndCost(selectCommodity);
    }
}
