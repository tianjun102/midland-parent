package com.midland.web.dao;

import com.midland.web.model.FeedbackEmail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackEmailMapper {

    FeedbackEmail selectFeedbackEmailById(Integer feedbackEmail);

    int deleteFeedbackEmailById(Integer feedbackEmail);

    int updateFeedbackEmailById(FeedbackEmail feedbackEmail);

    int insertFeedbackEmail(FeedbackEmail feedbackEmail);

    List<FeedbackEmail> findFeedbackEmailList(FeedbackEmail feedbackEmail);

    int batchUpdate(@Param("feedbackEmailList") List<FeedbackEmail> feedbackEmailList);
}
