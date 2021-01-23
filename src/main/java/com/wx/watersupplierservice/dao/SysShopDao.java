package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysShopPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysShopDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysShopDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysShopPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysShopPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysShopPo> list);

    int count(SysShopPo record);

	SysShopPo selectOne(SysShopPo record);

	List<SysShopPo> selectList(SysShopPo record);

	SysShopPo selectForUpdate(SysShopPo record);
}