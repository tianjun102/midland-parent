package com.midland.web.dao;

import com.midland.web.model.FeedbackEmail;
import java.util.List;

public interface FeedbackEmailMapper {

	FeedbackEmail selectFeedbackEmailById(Integer feedbackEmail);

	int deleteFeedbackEmailById(Integer feedbackEmail);

	int updateFeedbackEmailById(FeedbackEmail feedbackEmail);

	int insertFeedbackEmail(FeedbackEmail feedbackEmail);

	List<FeedbackEmail> findFeedbackEmailList(FeedbackEmail feedbackEmail);

}
