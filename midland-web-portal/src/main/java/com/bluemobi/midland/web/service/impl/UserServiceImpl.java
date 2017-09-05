package com.bluemobi.midland.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.bluemobi.midland.web.commons.core.util.ApplicationUtils;
import com.bluemobi.midland.web.commons.core.util.DateUtils;
import com.bluemobi.midland.web.commons.exception.ServiceException;
import com.bluemobi.midland.web.mapper.UserMapper;
import com.bluemobi.midland.web.model.User;
import com.bluemobi.midland.web.service.UserService;

/**   
* @Title: UserServiceImpl.java
* @Package com.bluemobi.midland.web.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author yek
* @date 2017年3月22日 上午8:55:26 
*/
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper userMapper;

	@Transactional(readOnly=true)
	@Override
	public int authentication(String phone) throws ServiceException {
		return userMapper.authentication(phone);
	}

	@Transactional(readOnly=true)
	@Override
	public User getUserByUsername(String username) throws ServiceException {
		List<User> list = userMapper.selectByUsername(username);
		if (null != list && list.size() == 1)
			return list.get(0);
		else {
			String msg = "系统根据用户名查询用户信息时出现多个用户名一致的用户。";
			ServiceException e = new ServiceException(msg);
			logger.error(msg, e);
			throw e;
		}
	}
	
	@Transactional(readOnly=true)
	@Override
	public User getUserByUserId(Integer userId) {
		
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int editPwdById(String newPwd, Integer id) throws ServiceException {
		
		return userMapper.updatePwdById(newPwd, id);
	}

	@Transactional
	@Override
	public int editUserById(User user) throws ServiceException {
		
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int addUser(Map<String, String> map) {
		User user = new User();
		user.setUsername(getUserCnName(map.get("userCnName"),map.get("createBy")));
		user.setUserCnName(map.get("userName"));
		user.setPassword(ApplicationUtils.sha256Hex("888888"));
		user.setUserType(3);
		user.setState(1);
		user.setPhone(map.get("phone"));
		user.setCreateTime(DateUtils.nowDate());
		user.setCreateBy(map.get("createBy"));
		return userMapper.insertSelective(user);
	}

	@Transactional(readOnly=true)
	@Override
	public PageList<User> findBycreateByPage(Map<String, String>  map, PageBounds pageBounds) {
		User  user = new User();
		user.setCreateBy(map.get("createBy"));
		user.setUserCnName(map.get("username"));
		user.setPhone(map.get("phone"));
		return userMapper.queryUserList(user,pageBounds);
	}

	@Transactional(readOnly=true)
	@Override
	public User findParentUserByChild(String username) {
		return userMapper.selectParentUserByChild(username);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User findtUserByEntity(User user) {
		return userMapper.selectUserByEntity(user);
	}

	//自增userName
	public String getUserCnName(String pUserName,String createUid){
		String temp = "";
		User  user =   userMapper.queryUserByCreateUid(createUid);  // 查询数据最后添加的用户的userName ,自增
		if(user!=null && StringUtils.isNotEmpty(user.getUsername())){
			String userName  = user.getUsername();
			int num = Integer.valueOf(userName.replace(pUserName+"-", ""));
			temp  = pUserName+ "-"+(num+1);
		}else{
			temp  = pUserName+ "-"+ 1;
		}
		return temp; 
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User queryUser(Integer uid) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(uid);
	}
	
	
	
}
