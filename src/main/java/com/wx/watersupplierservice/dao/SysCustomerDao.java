package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysCustomerPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysCustomerDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysCustomerDao extends BaseMapper {

	int insert(SysCustomerPo record);

    int insertList(List<SysCustomerPo> list);

    int updateList(List<SysCustomerPo> list);

	int update(SysCustomerPo record);

    int count(SysCustomerPo record);

	SysCustomerPo selectOne(SysCustomerPo record);

	List<SysCustomerPo> selectList(SysCustomerPo record);

	SysCustomerPo selectForUpdate(SysCustomerPo record);
}