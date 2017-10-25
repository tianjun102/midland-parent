package com.midland.web.dao;

import com.midland.web.model.Record;
import java.util.List;

public interface RecordMapper {

	Record selectRecordById(Integer record);

	int deleteRecordById(Integer record);

	int updateRecordById(Record record);

	int insertRecord(Record record);

	List<Record> findRecordList(Record record);

}
