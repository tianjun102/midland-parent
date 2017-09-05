package com.midland.web.service;

import com.midland.web.model.user.UserBlackLog;

import java.util.List;

/**
 * Created by 'ms.x' on 2017/8/8.
 */
public interface UserBlackLogService {
	
	
	 int deleteByPrimaryKey(Integer blackLogId) ;
	 int insert(UserBlackLog record) ;
	
	 int insertSelective(UserBlackLog record);
	
	 UserBlackLog selectByPrimaryKey(Integer blackLogId) ;
	
	 List<UserBlackLog> selectByUserId(Integer userId) ;
	 int updateByPrimaryKeySelective(UserBlackLog record) ;
	 int updateByPrimaryKey(UserBlackLog record) ;
}
