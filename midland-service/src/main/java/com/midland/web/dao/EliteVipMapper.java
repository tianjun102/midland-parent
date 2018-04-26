package com.midland.web.dao;

import com.midland.web.model.EliteVip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EliteVipMapper {

    EliteVip selectEliteVipById(Integer eliteVip);

    int deleteEliteVipById(Integer eliteVip);

    int updateEliteVipById(EliteVip eliteVip);
    int getCountByCateId(Integer cateId);

    int insertEliteVip(EliteVip eliteVip);

    List<EliteVip> findEliteVipList(EliteVip eliteVip);

    int batchUpdate(@Param("eliteVipList") List<EliteVip> eliteVipList);

}
