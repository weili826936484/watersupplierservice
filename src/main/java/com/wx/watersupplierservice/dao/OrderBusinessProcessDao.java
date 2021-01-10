package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.OrderBusinessProcessPo;
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

	int insert(OrderBusinessProcessPo record);

    int insertList(List<OrderBusinessProcessPo> list);

    int updateList(List<OrderBusinessProcessPo> list);

	int update(OrderBusinessProcessPo record);

    int count(OrderBusinessProcessPo record);

	OrderBusinessProcessPo selectOne(OrderBusinessProcessPo record);

	List<OrderBusinessProcessPo> selectList(OrderBusinessProcessPo record);

	OrderBusinessProcessPo selectForUpdate(OrderBusinessProcessPo record);
}