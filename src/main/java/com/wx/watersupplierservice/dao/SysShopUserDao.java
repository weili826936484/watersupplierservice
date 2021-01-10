package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysShopUserPo;
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

	int insert(SysShopUserPo record);

    int insertList(List<SysShopUserPo> list);

    int updateList(List<SysShopUserPo> list);

    int count(SysShopUserPo record);

	SysShopUserPo selectOne(SysShopUserPo record);

	List<SysShopUserPo> selectList(SysShopUserPo record);

	SysShopUserPo selectForUpdate(SysShopUserPo record);
}