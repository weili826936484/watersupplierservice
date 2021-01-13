package com.wx.watersupplierservice.controller;

import com.wx.watersupplierservice.po.SysShopSitePo;
import com.wx.watersupplierservice.po.SysWeixinPo;
import com.wx.watersupplierservice.service.WeixinService;
import com.wx.watersupplierservice.dao.SysShopSiteDao;
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
    @Autowired
    private WeixinService weixinService;

    @GetMapping("/list")
    public ObjectResults<List<SysShopSitePo>> a(){
        List<SysShopSitePo> all = sysShopSiteDao.findAll(SysShopSitePo.class);
        return ObjectResults.createSuccessResult(all);
    }
    @RequestMapping("/wx/save")
    public String saveWeixin() {
        //根据openId更新 或者保存微信基本信息
        SysWeixinPo weixinInfo = new SysWeixinPo();
        weixinInfo.setOpenid("2201902201");
        weixinInfo.setNickname("___wang乐1");
        weixinInfo.setHeadimgurl("http://192.168.0.108:8080/wx/error.html");
        weixinService.saveOrUpdateWx(weixinInfo);
        return "0o";
    }
}
