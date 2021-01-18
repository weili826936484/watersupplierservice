package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.OrderBusinessPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: OrderBusinessDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  OrderBusinessDao extends BaseMapper {

	int insert(OrderBusinessPo record);

    int insertList(List<OrderBusinessPo> list);

    int updateList(List<OrderBusinessPo> list);

    int count(OrderBusinessPo record);

	OrderBusinessPo selectOne(OrderBusinessPo record);

	List<OrderBusinessPo> selectList(OrderBusinessPo record);

	OrderBusinessPo selectForUpdate(OrderBusinessPo record);

    OrderBusinessPo findByOrderId(Integer orderId);
}