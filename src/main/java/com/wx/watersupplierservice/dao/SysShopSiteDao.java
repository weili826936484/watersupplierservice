package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.dto.UserShopSites;
import com.wx.watersupplierservice.po.SysShopSitePo;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.pojo.SysSitePojo;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.req.ShopsSiteReq;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysShopSiteDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysShopSiteDao extends BaseMapper {

    @VPSDataSource(DataSourceType.MASTER)
	int insert(SysShopSitePo record);

    @VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysShopSitePo> list);

    @VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysShopSitePo> list);

    int count(SysShopSitePo record);

	SysShopSitePo selectOne(SysShopSitePo record);

	List<SysShopSitePo> selectList(SysShopSitePo record);

	SysShopSitePo selectForUpdate(SysShopSitePo record);

    List<WatersPageDto.WatersDto> getSendWaterList(SendWatersReq sendWatersReq);

    int getSendWaterCount(SendWatersReq sendWatersReq);

    List<SysSitePojo> getSiteList(Integer userId);

    List<UserShopSites.UserShopSiteDto> getshopsites(ShopsSiteReq shopsSiteReq);
}