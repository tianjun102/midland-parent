package com.midland.web.service.impl;

import com.midland.web.dao.QuestionsMapper;
import com.midland.web.model.Questions;
import com.midland.web.service.QuestionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约看房Service实现类
 *
 * @author 
 * @since 2016年6月10日 下午12:05:03
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Resource
    private QuestionsMapper questionsMapper    ;
    
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return questionsMapper.deleteByPrimaryKey(id);
    }
     @Override
    public int deleteByIds(List<String> idlist) {
         Map<String,Object> map =new HashMap<>();
         map.put("idlist",idlist);
         return questionsMapper.deleteByIds(map);
    }
    
    @Override
    public int insertSelective(Questions record) {
        return questionsMapper.insertSelective(record);
    }
    
    @Override
    public Questions selectByPrimaryKey(Integer id) {
        return questionsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<Questions> questionPage(Questions questions) {
        return questionsMapper.questionPage(questions);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Questions record) {
        return questionsMapper.updateByPrimaryKeySelective(record);
    }
}
