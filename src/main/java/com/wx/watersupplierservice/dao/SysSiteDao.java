package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysSitePo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysSiteDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysSiteDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysSitePo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysSitePo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysSitePo> list);

    int count(SysSitePo record);

	SysSitePo selectOne(SysSitePo record);

	List<SysSitePo> selectList(SysSitePo record);

	SysSitePo selectForUpdate(SysSitePo record);
}