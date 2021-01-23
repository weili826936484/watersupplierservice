package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.WaterOrderProductPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: WaterOrderProductDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  WaterOrderProductDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(WaterOrderProductPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<WaterOrderProductPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<WaterOrderProductPo> list);

    int count(WaterOrderProductPo record);

	WaterOrderProductPo selectOne(WaterOrderProductPo record);

	List<WaterOrderProductPo> selectList(WaterOrderProductPo record);

	WaterOrderProductPo selectForUpdate(WaterOrderProductPo record);
}