package com.midland.web.service;

import com.midland.web.model.EntrustLog;

import java.util.List;

public interface EntrustLogService {

    /**
     * 主键查询
     **/
    EntrustLog selectEntrustLogByEntrustLogId(Integer entrustLogId);

    List<EntrustLog> selectEntrustLogByEntrustId(Integer entrustId);

    /**
     * 主键删除
     **/
    void deleteEntrustLogByEntrustLogId(Integer entrustLogId) throws Exception;

    /**
     * 主键更新
     **/
    void updateEntrustLogByEntrustLogId(EntrustLog entrustLog) throws Exception;

    /**
     * 插入
     **/
    void insertEntrustLog(EntrustLog entrustLog) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<EntrustLog> findEntrustLogList(EntrustLog entrustLog) throws Exception;

    void batchUpdate(List<EntrustLog> entrustLogList) throws Exception;

}
