package com.midland.web.dao;

import com.midland.web.model.Information;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationMapper {

    Information selectInformationById(Integer information);

    int deleteInformationById(Integer information);

    int updateInformationById(Information information);

    Information shiftTop(Information information);
    Information shiftUp(Information information);

    Information shiftDown(Information information);

    int insertInformation(Information information);

    List<Information> getByIdList(@Param("list") List<Integer> ids);

    List<Information> findInformationList(Information information);
    List<Information> findNewestInformationList(Information information);

    int batchUpdate(@Param("informationList") List<Information> informationList);

}
