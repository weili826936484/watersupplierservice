package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.OrderBusinessProcessPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: OrderBusinessProcessDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  OrderBusinessProcessDao extends BaseMapper {

	@VPSDataSource(DataSourceType.MASTER)
	int insert(OrderBusinessProcessPo record);

	@VPSDataSource(DataSourceType.MASTER)
    int insertList(List<OrderBusinessProcessPo> list);

	@VPSDataSource(DataSourceType.MASTER)
    int updateList(List<OrderBusinessProcessPo> list);

    int count(OrderBusinessProcessPo record);

	OrderBusinessProcessPo selectOne(OrderBusinessProcessPo record);

	List<OrderBusinessProcessPo> selectList(OrderBusinessProcessPo record);

	OrderBusinessProcessPo selectForUpdate(OrderBusinessProcessPo record);
}