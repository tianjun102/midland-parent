package com.bluemobi.midland.web.service;

import com.bluemobi.midland.web.commons.exception.ServiceException;
import com.bluemobi.midland.web.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.Map;

/**
 * @Title: UserService.java
 * @Package com.bluemobi.midland.web.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author yek
 * @date 2017年3月22日 上午8:55:12
 */
public interface UserService {
	
	/**
	 * 验证该用户手机号唯一性
	 * 
	 * @param phone
	 *            手机号
	 * @return int
	 * @throws ServiceException
	 */
	int authentication(String phone) throws ServiceException;

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	User getUserByUserId(Integer userId);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 *            用户名
	 * @return User
	 * @throws ServiceException
	 */
	User getUserByUsername(String username) throws ServiceException;

	/**
	 * 修改用户密码
	 * 
	 * @param newPwd
	 *            新用户密码
	 * @param id
	 *            用户ID
	 * @return int
	 * @throws ServiceException
	 */
	int editPwdById(String newPwd, Integer id) throws ServiceException;

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 *            用户信息参数对象
	 * @return
	 * @throws ServiceException
	 */
	int editUserById(User user) throws ServiceException;
	
	/**
     * 新增
     * @param map
     * @return
     */
	int addUser(Map<String, String> map);
	
	/**
	 * 查询安装人员信息
	 * @param pageBounds 分页对象
	 * @return
	 */
	PageList<User> findBycreateByPage(Map<String, String> map, PageBounds pageBounds);
	
	/**
	 * 根据子级额户查询父级用户ID
	 * @param username 用户名
	 * @return
	 */
	User findParentUserByChild(String username);
	
	public int  updateUser(User user);
	
	public User findtUserByEntity(User user);
	
	
	//修改用户信息
	int  modifyUser(User user);
	
	
	//查询用户明细信息
	User  queryUser(Integer uid);
}
