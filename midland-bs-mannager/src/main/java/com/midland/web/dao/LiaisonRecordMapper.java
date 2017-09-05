package com.midland.web.dao;

import com.midland.web.model.LiaisonRecord;
import java.util.List;

public interface LiaisonRecordMapper {

	LiaisonRecord selectLiaisonRecordById(Integer liaisonRecord);

	int deleteLiaisonRecordById(Integer liaisonRecord);

	int updateLiaisonRecordById(LiaisonRecord liaisonRecord);

	int insertLiaisonRecord(LiaisonRecord liaisonRecord);

	List<LiaisonRecord> findLiaisonRecordList(LiaisonRecord liaisonRecord);

}
