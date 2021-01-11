package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysSiteUserPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysSiteUserDao
 * @author 82693
 * @date 2021年01月11日
 */
@Repository
public interface  SysSiteUserDao {

	int insert(SysSiteUserPo record);

    int insertList(List<SysSiteUserPo> list);

    int updateList(List<SysSiteUserPo> list);

    int count(SysSiteUserPo record);

	SysSiteUserPo selectOne(SysSiteUserPo record);

	List<SysSiteUserPo> selectList(SysSiteUserPo record);

	SysSiteUserPo selectForUpdate(SysSiteUserPo record);
}