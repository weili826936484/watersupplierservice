package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysUserPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysUserDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysUserDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(SysUserPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysUserPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysUserPo> list);

    int count(SysUserPo record);

	SysUserPo selectOne(SysUserPo record);

	List<SysUserPo> selectList(SysUserPo record);

	SysUserPo selectForUpdate(SysUserPo record);
}