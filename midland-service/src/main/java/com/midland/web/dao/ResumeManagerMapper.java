package com.midland.web.dao;

import com.midland.web.model.RecruitManager;
import com.midland.web.model.ResumeManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResumeManagerMapper {

	ResumeManager selectResumeManagerById(Integer resumeManager);

	int deleteResumeManagerById(Integer resumeManager);

	int updateResumeManagerById(ResumeManager resumeManager);

	int insertResumeManager(ResumeManager resumeManager);

	List<ResumeManager> findResumeManagerList(ResumeManager resumeManager);

	int batchUpdate(@Param("resumeManagerList") List<ResumeManager> resumeManagerList);


}
