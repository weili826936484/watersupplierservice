package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.WaterOrderDiscountPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: WaterOrderDiscountDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  WaterOrderDiscountDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(WaterOrderDiscountPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<WaterOrderDiscountPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<WaterOrderDiscountPo> list);

    int count(WaterOrderDiscountPo record);

	WaterOrderDiscountPo selectOne(WaterOrderDiscountPo record);

	List<WaterOrderDiscountPo> selectList(WaterOrderDiscountPo record);

	WaterOrderDiscountPo selectForUpdate(WaterOrderDiscountPo record);
}