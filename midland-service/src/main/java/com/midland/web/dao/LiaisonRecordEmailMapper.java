package com.midland.web.dao;

import com.midland.web.model.LeaveMsg;
import com.midland.web.model.LiaisonRecordEmail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LiaisonRecordEmailMapper {

	LiaisonRecordEmail selectLiaisonRecordEmailById(Integer liaisonRecordEmail);

	int deleteLiaisonRecordEmailById(Integer liaisonRecordEmail);

	int updateLiaisonRecordEmailById(LiaisonRecordEmail liaisonRecordEmail);

	int insertLiaisonRecordEmail(LiaisonRecordEmail liaisonRecordEmail);

	List<LiaisonRecordEmail> findLiaisonRecordEmailList(LiaisonRecordEmail liaisonRecordEmail);

	int batchUpdate(@Param("liaisonRecordEmailList") List<LiaisonRecordEmail> liaisonRecordEmailList);

}
