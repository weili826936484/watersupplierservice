package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysOrgPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysOrgDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysOrgDao extends BaseMapper {

	int insert(SysOrgPo record);

    int insertList(List<SysOrgPo> list);

    int updateList(List<SysOrgPo> list);

	int update(SysOrgPo record);

    int count(SysOrgPo record);

	SysOrgPo selectOne(SysOrgPo record);

	List<SysOrgPo> selectList(SysOrgPo record);

	SysOrgPo selectForUpdate(SysOrgPo record);
}