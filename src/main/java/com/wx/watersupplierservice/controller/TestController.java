package com.wx.watersupplierservice.controller;

import com.wx.watersupplierservice.dao.SysShopSiteDao;
import com.wx.watersupplierservice.po.SysShopSitePo;
import com.wx.watersupplierservice.resp.ObjectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    /**
     * 最好是service，再调dao
     */
    @Autowired
    private SysShopSiteDao sysShopSiteDao;
    @GetMapping("/list")
    public ObjectResults<List<SysShopSitePo>> a(){
        List<SysShopSitePo> all = sysShopSiteDao.findAll(SysShopSitePo.class);
        return ObjectResults.createSuccessResult(all);
    }
}
