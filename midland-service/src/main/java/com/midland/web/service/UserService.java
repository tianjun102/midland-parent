package com.midland.web.service;

import java.util.List;

import com.midland.core.generic.GenericService;
import com.midland.web.model.user.User;

/**
 * 用户 业务 接口
 * 
 * @author 
 * @since 2016年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Integer> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);
    
    /**
     * 根据搜索条件 查询用户列表
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 列表分页条件查询
     * @param user
     * @return
     */
    List<User> selectByExampleAndPage(User user);
   

    /**
     * 新增
     * @param user
     * @return
     */
	void addUser(User user) throws Exception;

	/**
	 * 修改
	 * @param user
	 * @return
	 */
	int modifyUser(User user);
	
	/**
	 * 更新用户角色关系
	 * @param userId
	 * @param userRoles
	 * @return
	 */
	int updateUserRole(Integer userId, String userRoles);
	
	List<User> selectUsersByRoleId(Integer roleId);

	User selectByUser(User user);
}
