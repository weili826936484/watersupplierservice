package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.dto.WatersDto;
import com.wx.watersupplierservice.po.SysShopSitePo;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysShopSiteDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysShopSiteDao extends BaseMapper {

	int insert(SysShopSitePo record);

    int insertList(List<SysShopSitePo> list);

    int updateList(List<SysShopSitePo> list);

    int count(SysShopSitePo record);

	SysShopSitePo selectOne(SysShopSitePo record);

	List<SysShopSitePo> selectList(SysShopSitePo record);

	SysShopSitePo selectForUpdate(SysShopSitePo record);

    List<WatersDto> getSendWaterList(SendWatersReq sendWatersReq);
}