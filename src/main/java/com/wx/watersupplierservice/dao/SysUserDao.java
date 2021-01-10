package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysUserPo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysUserDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysUserDao extends BaseMapper {

	int insert(SysUserPo record);

    int insertList(List<SysUserPo> list);

    int updateList(List<SysUserPo> list);

    int count(SysUserPo record);

	SysUserPo selectOne(SysUserPo record);

	List<SysUserPo> selectList(SysUserPo record);

	SysUserPo selectForUpdate(SysUserPo record);
}