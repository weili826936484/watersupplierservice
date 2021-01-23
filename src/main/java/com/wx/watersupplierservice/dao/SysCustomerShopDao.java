package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysCustomerShopPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysCustomerShopDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysCustomerShopDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysCustomerShopPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysCustomerShopPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysCustomerShopPo> list);

    int count(SysCustomerShopPo record);

	SysCustomerShopPo selectOne(SysCustomerShopPo record);

	List<SysCustomerShopPo> selectList(SysCustomerShopPo record);

	SysCustomerShopPo selectForUpdate(SysCustomerShopPo record);
}