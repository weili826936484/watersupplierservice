package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysOrgUserPo;
import com.wx.watersupplierservice.pojo.SysOrgPojo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysOrgUserDao
 * @author 82693
 * @date 2021年01月11日
 */
@Repository
public interface  SysOrgUserDao {

    @VPSDataSource(DataSourceType.MASTER)
	int insert(SysOrgUserPo record);

    @VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysOrgUserPo> list);

    @VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysOrgUserPo> list);

    int count(SysOrgUserPo record);

	SysOrgUserPo selectOne(SysOrgUserPo record);

	List<SysOrgUserPo> selectList(SysOrgUserPo record);

	SysOrgUserPo selectForUpdate(SysOrgUserPo record);

    List<SysOrgPojo> getOrgBaseInfo(Integer userId);
}