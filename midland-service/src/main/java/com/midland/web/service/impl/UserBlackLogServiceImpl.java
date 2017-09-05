package com.midland.web.service.impl;

import com.midland.web.dao.UserBlackLogMapper;
import com.midland.web.model.user.UserBlackLog;
import com.midland.web.service.UserBlackLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 预约看房Service实现类
 *
 * @author 
 * @since 2016年6月10日 下午12:05:03
 */
@Service
public class UserBlackLogServiceImpl implements UserBlackLogService {

    @Resource
    private UserBlackLogMapper userBlackLogMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer blackLogId) {
        return userBlackLogMapper.deleteByPrimaryKey(blackLogId);
    }
    
    @Override
    public int insert(UserBlackLog record) {
        return userBlackLogMapper.insert(record);
    }
    
    @Override
    public int insertSelective(UserBlackLog record) {
        return userBlackLogMapper.insertSelective(record);
    }
    
    @Override
    public UserBlackLog selectByPrimaryKey(Integer blackLogId) {
        return userBlackLogMapper.selectByPrimaryKey(blackLogId);
    }
    
    @Override
    public List<UserBlackLog> selectByUserId(Integer userId) {
        return userBlackLogMapper.selectByUserId(userId);
    }
    
    @Override
    public int updateByPrimaryKeySelective(UserBlackLog record) {
        return userBlackLogMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(UserBlackLog record) {
        return userBlackLogMapper.updateByPrimaryKey(record);
    }
}
