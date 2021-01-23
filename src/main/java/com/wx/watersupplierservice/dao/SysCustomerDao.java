package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysCustomerPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysCustomerDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysCustomerDao extends BaseMapper {

    @VPSDataSource(DataSourceType.MASTER)
	int insert(SysCustomerPo record);

    @VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysCustomerPo> list);

    @VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysCustomerPo> list);

    int count(SysCustomerPo record);

	SysCustomerPo selectOne(SysCustomerPo record);

	List<SysCustomerPo> selectList(SysCustomerPo record);

	SysCustomerPo selectForUpdate(SysCustomerPo record);

    SysCustomerPo checkExists(@Param("platform") String platform, @Param("buyerpin")String buyerpin);
}