package com.wx.watersupplierservice.service;

import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.dto.UserShopSites;
import com.wx.watersupplierservice.dto.UseroOrderPageDto;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.po.SysShopPo;
import com.wx.watersupplierservice.req.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author : weili
 * @version V1.0
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.service
 * @Description: TODO
 * @date Date : 2021年01月11日 23:05
 */
public interface BusinessService {

    WatersPageDto getSendWaterList(SendWatersReq sendWatersReq);

    List<UserShopDto> getUserShopList(ShopsReq userId);

    UseroOrderPageDto getOrderList(OrderListReq getOrderListReq);

    void changeOrder(ChangeOrderReq changeOrder) throws UnsupportedEncodingException;

    void updateUserShop(SysShopPo sysShopPo);

    void deleteUserShop(Integer sysShopUserId);

    List<UserShopSites.UserShopSiteDto> getShopSiteList(ShopsSiteReq shopsSiteReq);

    void updateShopSite(UserShopSites.UserShopSiteDto userShopSiteDto);
}
