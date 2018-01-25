package com.midland.web.dao;

import com.midland.web.model.Entrust;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrustMapper {

    Entrust selectEntrustById(Integer entrust);

    int deleteEntrustById(Integer entrust);

    int updateEntrustById(Entrust entrust);

    int insertEntrust(Entrust entrust);

    List<Entrust> findEntrustList(Entrust entrust);

    int batchUpdate(@Param("entrustList") List<Entrust> entrustList);

}
