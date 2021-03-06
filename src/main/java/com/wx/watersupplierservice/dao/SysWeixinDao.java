package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysWeixinPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysWeixinDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysWeixinDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysWeixinPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysWeixinPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysWeixinPo> list);

    int count(SysWeixinPo record);

	SysWeixinPo selectOne(SysWeixinPo record);

	List<SysWeixinPo> selectList(SysWeixinPo record);

	SysWeixinPo selectForUpdate(SysWeixinPo record);
}