package com.midland.web.service.impl;

import com.midland.web.service.AttentionService;
import com.midland.web.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    private AttentionService attentionServiceImpl;
    @Autowired
    private QuestionsService questionsServiceImpl;

}
