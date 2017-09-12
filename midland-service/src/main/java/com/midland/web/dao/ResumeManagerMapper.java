package com.midland.web.dao;

import com.midland.web.model.ResumeManager;
import java.util.List;

public interface ResumeManagerMapper {

	ResumeManager selectResumeManagerById(Integer resumeManager);

	int deleteResumeManagerById(Integer resumeManager);

	int updateResumeManagerById(ResumeManager resumeManager);

	int insertResumeManager(ResumeManager resumeManager);

	List<ResumeManager> findResumeManagerList(ResumeManager resumeManager);

}
