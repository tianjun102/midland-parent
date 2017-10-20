package com.midland.web.dao;

import com.midland.web.model.LiaisonRecord;
import com.midland.web.model.LiaisonRecordEmail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LiaisonRecordMapper {

	LiaisonRecord selectLiaisonRecordById(Integer liaisonRecord);

	int deleteLiaisonRecordById(Integer liaisonRecord);

	int updateLiaisonRecordById(LiaisonRecord liaisonRecord);

	int insertLiaisonRecord(LiaisonRecord liaisonRecord);

	List<LiaisonRecord> findLiaisonRecordList(LiaisonRecord liaisonRecord);

	int batchUpdate(@Param("liaisonRecordList") List<LiaisonRecord> liaisonRecordList);


}
