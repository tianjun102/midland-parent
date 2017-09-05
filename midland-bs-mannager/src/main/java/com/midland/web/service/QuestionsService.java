package com.midland.web.service;

import com.midland.web.model.Questions;

import java.util.List;

/**
 *
 * 
 * @author 
 * @since 2016年6月10日 下午12:02:39
 **/
public interface QuestionsService {
    int deleteByPrimaryKey(Integer id);
	
	int deleteByIds(List<String> ids);
	
	int insertSelective(Questions record);
    Questions selectByPrimaryKey(Integer id);
    List<Questions> questionPage(Questions questions);
    int updateByPrimaryKeySelective(Questions record);
}
