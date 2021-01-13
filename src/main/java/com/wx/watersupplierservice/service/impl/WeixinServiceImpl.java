package com.wx.watersupplierservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.watersupplierservice.dao.SysWeixinDao;
import com.wx.watersupplierservice.po.SysWeixinPo;
import com.wx.watersupplierservice.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private SysWeixinDao sysWeixinDao;

	@Override
	public void saveOrUpdateWx(SysWeixinPo record) {
		
		SysWeixinPo weixinInfo = sysWeixinDao.selectOne(record);

		if (weixinInfo ==null) {
			sysWeixinDao.insert(record);
		} else {
			sysWeixinDao.update(record);
		}
	}

}
