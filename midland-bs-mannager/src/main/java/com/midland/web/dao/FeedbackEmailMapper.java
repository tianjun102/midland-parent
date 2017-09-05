package com.midland.web.dao;

import com.midland.web.model.FeedbackEmail;

public interface FeedbackEmailMapper {
    int insert(FeedbackEmail record);

    int insertSelective(FeedbackEmail record);
}