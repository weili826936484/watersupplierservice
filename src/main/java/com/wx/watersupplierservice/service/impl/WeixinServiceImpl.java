package com.wx.watersupplierservice.service.impl;

import com.wx.watersupplierservice.dao.OrderBusinessDao;
import com.wx.watersupplierservice.dao.SysWeixinDao;
import com.wx.watersupplierservice.po.OrderBusinessPo;
import com.wx.watersupplierservice.po.SysWeixinPo;
import com.wx.watersupplierservice.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private SysWeixinDao sysWeixinDao;

	@Autowired
	private OrderBusinessDao orderBusinessDao;
	@Override
	public void saveOrUpdateWx(SysWeixinPo record) {
		OrderBusinessPo byId = orderBusinessDao.findById(OrderBusinessPo.class, 1);
		byId.setCreateTime(new Date());
		orderBusinessDao.update(byId);
		SysWeixinPo weixinInfo = sysWeixinDao.findById(SysWeixinPo.class,record.getOpenid());

		if (weixinInfo ==null) {
			sysWeixinDao.insert(record);
		} else {
			sysWeixinDao.update(record);
		}
	}

}
