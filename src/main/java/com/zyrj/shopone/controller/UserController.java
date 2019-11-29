package com.zyrj.shopone.controller;


import com.zyrj.shopone.entity.Address;
import com.zyrj.shopone.entity.User;
import com.zyrj.shopone.service.AddressService;
import com.zyrj.shopone.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(description = "用户")
@RequestMapping(value = "user")
@RestController
public class UserController {


    @Autowired
    private UserService importUserServiceImpl;

    @Autowired
    private AddressService addressService;


    @ApiOperation(value = "Specification通过id查询")
    @PostMapping("/findById")
    public List<User> findById(Integer id) {
        return importUserServiceImpl.findById(id);
    }


    @ApiOperation(value = "Specification通过Id区间查询用户信息")
    @PostMapping("/betweenById")
    public List<User> betweenById(Integer beginId, Integer endId) {
        return importUserServiceImpl.betweenById(beginId, endId);
    }

    @ApiOperation(value = "Specification通过Id进行or查询用户信息")
    @PostMapping("/inById")
    public List<User> inById(Integer [] ids) {
        return importUserServiceImpl.inById(ids);
    }


    @ApiOperation(value = "Specification模仿多条件搜索框查询用户信息")
    @PostMapping("/findByUser")
    public List<User> findByUser(@RequestBody User user) {
        return importUserServiceImpl.findByUser(user);
    }

    @ApiOperation(value = "Specification查询手机号含某些数字的")
    @PostMapping("/likeByPhone")
    public List<User> likeFindByPhone(String phone) {
        List<User> userList = importUserServiceImpl.likeByPhone(phone);
        return userList;
    }

    @ApiOperation(value = "Specification通过手机号（账号） 查询用户所有收货地址")
    @PostMapping("/findAddressByPhone")
    public List<Address> findAddressByPhone(String phone) {
        List<User> userList = importUserServiceImpl.findAddressByPhone(phone);
        List<Address> addressList = null;
        for (User u : userList
             ) {
            addressList = addressService.findByUId(u.getUId());
        }
        return addressList;
    }


    @ApiOperation(value = "Specification查询结果进行分页")
    @PostMapping("/findAllAndPage")
    public Page<User> findAllAndPage(Integer page, Integer size) {
        Page<User> userList = importUserServiceImpl.findAllAndPage(page-1,size);
        return userList;
    }

    @ApiOperation(value = "EasyPoi Excel表导入")
    @PostMapping("/importUserExcel")
    public void importUserExcel(@RequestBody MultipartFile file) throws Exception {


        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //excel的数据
        List<User> list = ExcelImportUtil.importExcel(
                file.getInputStream(),
                User.class, params);

        importUserServiceImpl.saveAll(list);
    }

    @ApiOperation(value = "EasyPoi用户数据导出")
    @GetMapping("/exportCommodityExcel")
    public void exportCommodityExcel(HttpServletResponse response) throws Exception {

        //模拟从数据库获取需要导出的数据
        List<User> userList = importUserServiceImpl.findAll();

        ExportParams params = new ExportParams("商品信息", "测试");
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, userList);
        response.setHeader("content", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
        response.setCharacterEncoding("UTF-8");

        workbook.write(response.getOutputStream());

    }
}
