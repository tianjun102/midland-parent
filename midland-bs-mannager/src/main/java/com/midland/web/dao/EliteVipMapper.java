package com.midland.web.dao;

import com.midland.web.model.EliteVip;

public interface EliteVipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EliteVip record);

    int insertSelective(EliteVip record);

    EliteVip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EliteVip record);

    int updateByPrimaryKey(EliteVip record);
}