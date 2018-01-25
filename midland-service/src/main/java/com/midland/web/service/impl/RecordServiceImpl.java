package com.midland.web.service.impl;

import com.midland.web.dao.RecordMapper;
import com.midland.web.model.Record;
import com.midland.web.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 插入
     **/
    @Override
    public void insertRecord(Record record) throws Exception {
        try {
            log.info("insert {}", record);
            recordMapper.insertRecord(record);
        } catch (Exception e) {
            log.error("insertRecord异常 {}", record, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Record selectRecordById(Integer id) {
        log.info("selectRecordById  {}", id);
        return recordMapper.selectRecordById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteRecordById(Integer id) throws Exception {
        try {
            log.info("deleteRecordById  {}", id);
            int result = recordMapper.deleteRecordById(id);
            if (result < 1) {
                throw new Exception("deleteRecordById失败");
            }
        } catch (Exception e) {
            log.error("deleteRecordById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateRecordById(Record record) throws Exception {
        try {
            log.info("updateRecordById  {}", record);
            int result = recordMapper.updateRecordById(record);
            if (result < 1) {
                throw new Exception("updateRecordById失败");
            }
        } catch (Exception e) {
            log.error("updateRecordById  {}", record, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Record> findRecordList(Record record) throws Exception {
        try {
            log.info("findRecordList  {}", record);
            return recordMapper.findRecordList(record);
        } catch (Exception e) {
            log.error("findRecordList  {}", record, e);
            throw e;
        }
    }
}
