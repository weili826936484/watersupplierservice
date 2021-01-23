package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysSiteUserPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysSiteUserDao
 * @author 82693
 * @date 2021年01月11日
 */
@Repository
public interface  SysSiteUserDao {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysSiteUserPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysSiteUserPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysSiteUserPo> list);

    int count(SysSiteUserPo record);

	SysSiteUserPo selectOne(SysSiteUserPo record);

	List<SysSiteUserPo> selectList(SysSiteUserPo record);

	SysSiteUserPo selectForUpdate(SysSiteUserPo record);
}