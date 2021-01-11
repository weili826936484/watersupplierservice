package com.wx.watersupplierservice.service.impl;/**
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.service.impl
 * @Description: TODO
 * @author : weili
 * @date Date : 2021年01月11日 23:06
 * @version V1.0
 */

import com.wx.watersupplierservice.dao.SysShopSiteDao;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.exception.PublicException;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @program: BusinessServiceImpl
 *
 * @description:
 *
 * @author: weili
 *
 * @create: 2021-01-11 23:06
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private SysShopSiteDao sysShopSiteDao;
    @Override
    public WatersPageDto getSendWaterList(SendWatersReq sendWatersReq) {
        if (Objects.isNull(sendWatersReq) || Objects.isNull(sendWatersReq.getPageIndex())
                || Objects.isNull(sendWatersReq.getSiteId()) || Objects.isNull(sendWatersReq.getPageSize())){
            throw new PublicException("参数不全...");
        }
        int offset = (sendWatersReq.getPageIndex()-1) * sendWatersReq.getPageSize();
        sendWatersReq.setOffset(offset);
        int count = sysShopSiteDao.getSendWaterCount(sendWatersReq);
        List<WatersPageDto.WatersDto> list = sysShopSiteDao.getSendWaterList(sendWatersReq);
        WatersPageDto watersPageDto = new WatersPageDto();
        watersPageDto.setCount(count);
        watersPageDto.setList(list);
        watersPageDto.setPageIndex(sendWatersReq.getPageIndex());
        watersPageDto.setPageSize(sendWatersReq.getPageSize());
        return watersPageDto;
    }
}
