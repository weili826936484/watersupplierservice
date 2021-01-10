package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.WaterOrderProductPo;
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

	int insert(WaterOrderProductPo record);

    int insertList(List<WaterOrderProductPo> list);

    int updateList(List<WaterOrderProductPo> list);

	int update(WaterOrderProductPo record);

    int count(WaterOrderProductPo record);

	WaterOrderProductPo selectOne(WaterOrderProductPo record);

	List<WaterOrderProductPo> selectList(WaterOrderProductPo record);

	WaterOrderProductPo selectForUpdate(WaterOrderProductPo record);
}