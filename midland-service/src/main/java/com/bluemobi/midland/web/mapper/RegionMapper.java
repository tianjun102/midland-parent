package com.bluemobi.midland.web.mapper;


import com.bluemobi.midland.web.model.Region;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer regionId);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer regionId);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}