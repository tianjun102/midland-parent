package com.midland.web.dao;

import com.midland.web.model.AppointLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointLogMapper {

    AppointLog selectAppointLogByAppointLogId(Integer appointLogId);

    List<AppointLog> selectAppointLogByAppointId(Integer appointId);


    int deleteAppointLogByAppointLogId(Integer appointLogId);

    int updateAppointLogByAppointLogId(AppointLog appointLog);

    int insertAppointLog(AppointLog appointLog);

    List<AppointLog> findAppointLogList(AppointLog appointLog);

    int batchUpdate(@Param("appointLogList") List<AppointLog> appointLogList);

}
