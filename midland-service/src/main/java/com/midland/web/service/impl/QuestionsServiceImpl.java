package com.midland.web.service.impl;

import com.midland.web.dao.QuestionsMapper;
import com.midland.web.model.Questions;
import com.midland.web.service.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(PopularServiceImpl.class);
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

    @Override
    public void batchUpdate(List<Questions> questionsList) throws Exception {
        try {
            log.debug("batchUpdate  {}",questionsList);
            int result = questionsMapper.batchUpdate(questionsList);
            if (result < 1) {
                throw new Exception("batchUpdate失败");
            }
        } catch(Exception e) {
            log.error("batchUpdate  {}",questionsList,e);
            throw e;
        }
    }
}
