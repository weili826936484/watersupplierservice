package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysCustomerShopPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysCustomerShopDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysCustomerShopDao extends BaseMapper {

	int insert(SysCustomerShopPo record);

    int insertList(List<SysCustomerShopPo> list);

    int updateList(List<SysCustomerShopPo> list);

	int update(SysCustomerShopPo record);

    int count(SysCustomerShopPo record);

	SysCustomerShopPo selectOne(SysCustomerShopPo record);

	List<SysCustomerShopPo> selectList(SysCustomerShopPo record);

	SysCustomerShopPo selectForUpdate(SysCustomerShopPo record);
}