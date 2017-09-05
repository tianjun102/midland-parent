package com.midland.web.dao;

import com.midland.web.model.user.UserBlackLog;

import java.util.List;

public interface UserBlackLogMapper {
    int deleteByPrimaryKey(Integer blackLogId);

    int insert(UserBlackLog record);

    int insertSelective(UserBlackLog record);

    UserBlackLog selectByPrimaryKey(Integer blackLogId);
    List<UserBlackLog> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserBlackLog record);

    int updateByPrimaryKey(UserBlackLog record);
}