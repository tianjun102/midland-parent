package com.midland.web.dao;

import com.midland.web.model.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper {

    Record selectRecordById(Integer record);

    int deleteRecordById(Integer record);

    int updateRecordById(Record record);

    int insertRecord(Record record);

    List<Record> findRecordList(Record record);

}
