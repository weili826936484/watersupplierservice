package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.WaterOrderPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: WaterOrderDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  WaterOrderDao extends BaseMapper {

	int insert(WaterOrderPo record);

    int insertList(List<WaterOrderPo> list);

    int updateList(List<WaterOrderPo> list);

	int update(WaterOrderPo record);

    int count(WaterOrderPo record);

	WaterOrderPo selectOne(WaterOrderPo record);

	List<WaterOrderPo> selectList(WaterOrderPo record);

	WaterOrderPo selectForUpdate(WaterOrderPo record);
}