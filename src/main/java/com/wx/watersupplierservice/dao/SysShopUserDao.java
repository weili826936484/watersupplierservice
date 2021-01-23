package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.po.SysShopUserPo;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.VPSDataSource;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysShopUserDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysShopUserDao extends BaseMapper {

    @VPSDataSource(DataSourceType.MASTER)
	int insert(SysShopUserPo record);

    @VPSDataSource(DataSourceType.MASTER)
    int insertList(List<SysShopUserPo> list);

    @VPSDataSource(DataSourceType.MASTER)
    int updateList(List<SysShopUserPo> list);

    int count(SysShopUserPo record);

	SysShopUserPo selectOne(SysShopUserPo record);

	List<SysShopUserPo> selectList(SysShopUserPo record);

	SysShopUserPo selectForUpdate(SysShopUserPo record);

    List<UserShopDto> getUserShopList(Integer userId);
}