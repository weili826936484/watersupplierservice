package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysOrgUserPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysOrgUserDao
 * @author 82693
 * @date 2021年01月11日
 */
@Repository
public interface  SysOrgUserDao {

	int insert(SysOrgUserPo record);

    int insertList(List<SysOrgUserPo> list);

    int updateList(List<SysOrgUserPo> list);

    int count(SysOrgUserPo record);

	SysOrgUserPo selectOne(SysOrgUserPo record);

	List<SysOrgUserPo> selectList(SysOrgUserPo record);

	SysOrgUserPo selectForUpdate(SysOrgUserPo record);
}