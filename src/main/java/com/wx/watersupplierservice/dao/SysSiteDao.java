package com.wx.watersupplierservice.dao;

import com.wx.watersupplierservice.po.SysSitePo;
import com.xdf.pscommon.dao.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: SysSiteDao
 * @author 82693
 * @date 2021年01月10日
 */
@Repository
public interface  SysSiteDao extends BaseMapper {

	int insert(SysSitePo record);

    int insertList(List<SysSitePo> list);

    int updateList(List<SysSitePo> list);

	int update(SysSitePo record);

    int count(SysSitePo record);

	SysSitePo selectOne(SysSitePo record);

	List<SysSitePo> selectList(SysSitePo record);

	SysSitePo selectForUpdate(SysSitePo record);
}