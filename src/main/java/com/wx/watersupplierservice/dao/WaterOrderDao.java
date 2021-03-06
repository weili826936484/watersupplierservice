package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.dto.OrderDto;
import com.wx.watersupplierservice.po.WaterOrderPo;
import com.wx.watersupplierservice.req.OrderListReq;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: WaterOrderDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface WaterOrderDao extends BaseMapper {

    @VPSDataSource(DataSourceType.MASTER)
	int insert(WaterOrderPo record);

    @VPSDataSource(DataSourceType.MASTER)
    int insertList(List<WaterOrderPo> list);

    @VPSDataSource(DataSourceType.MASTER)
    int updateList(List<WaterOrderPo> list);

    int count(WaterOrderPo record);

	WaterOrderPo selectOne(WaterOrderPo record);

	List<WaterOrderPo> selectList(WaterOrderPo record);

	WaterOrderPo selectForUpdate(WaterOrderPo record);

    List<OrderDto> getOrgOrderList(OrderListReq orderListReq);

    List<OrderDto> getOrgOrderListForSite(OrderListReq orderListReq);

    @VPSDataSource(DataSourceType.MASTER)
    int updateStatusById(@Param("id") Integer id, @Param("code") String code, @Param("version")Integer version, @Param("userId")Integer userId);

    List<OrderDto.OrderSiteBefor> getOrderSiteBeforsList(String custom);

    Integer getOrgOrderCount(OrderListReq orderListReq);
}