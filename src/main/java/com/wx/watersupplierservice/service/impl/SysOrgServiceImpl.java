package com.wx.watersupplierservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.watersupplierservice.dao.SysOrgDao;
import com.wx.watersupplierservice.po.SysOrgPo;
import com.wx.watersupplierservice.service.SysOrgService;

@Service
public class SysOrgServiceImpl implements SysOrgService{

	@Autowired
	private SysOrgDao sysOrgDao;
	
	public SysOrgPo findSysOrg(SysOrgPo orgInfo) {
		
		return sysOrgDao.selectOne(orgInfo);
	}

}
