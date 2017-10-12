package com.midland.web.dao;

import com.midland.web.model.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedbackMapper {

	Feedback selectFeedbackById(Integer feedback);

	int deleteFeedbackById(Integer feedback);

	int updateFeedbackById(Feedback feedback);

	int insertFeedback(Feedback feedback);

	List<Feedback> findFeedbackList(Feedback feedback);

}
