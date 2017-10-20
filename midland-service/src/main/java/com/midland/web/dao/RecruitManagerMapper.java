package com.midland.web.dao;

import com.midland.web.model.QuotationSecondHand;
import com.midland.web.model.RecruitManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecruitManagerMapper {

	RecruitManager selectRecruitManagerById(Integer recruitManager);

	int deleteRecruitManagerById(Integer recruitManager);

	int updateRecruitManagerById(RecruitManager recruitManager);

	int insertRecruitManager(RecruitManager recruitManager);

	List<RecruitManager> findRecruitManagerList(RecruitManager recruitManager);

	int batchUpdate(@Param("recruitManagerList") List<RecruitManager> recruitManagerList);


}
