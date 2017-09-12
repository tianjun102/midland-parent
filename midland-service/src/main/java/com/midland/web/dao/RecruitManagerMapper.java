package com.midland.web.dao;

import com.midland.web.model.RecruitManager;
import java.util.List;

public interface RecruitManagerMapper {

	RecruitManager selectRecruitManagerById(Integer recruitManager);

	int deleteRecruitManagerById(Integer recruitManager);

	int updateRecruitManagerById(RecruitManager recruitManager);

	int insertRecruitManager(RecruitManager recruitManager);

	List<RecruitManager> findRecruitManagerList(RecruitManager recruitManager);

}
