package com.midland.web.dao;

import com.midland.web.model.EliteClub;

public interface EliteClubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EliteClub record);

    int insertSelective(EliteClub record);

    EliteClub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EliteClub record);

    int updateByPrimaryKeyWithBLOBs(EliteClub record);

    int updateByPrimaryKey(EliteClub record);
}