package com.midland.web.dao;

import com.midland.web.commons.core.generic.GenericDao;
import com.midland.web.model.WebUser;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WebUserMapper extends GenericDao<WebUser, Integer> {

    /**
	 * 验证该用户手机号唯一性
	 * 
	 * @param phone
	 *            手机号
	 * @return int
	 */
	int authentication(String phone);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return List<User>
	 */
	List<WebUser> selectByUsername(String username);

	/**
	 * 修改用户密码
	 * 
	 * @param newPwd
	 *            新用户密码
	 * @param id
	 *            用户ID
	 * @return int
	 */
	int updatePwdById(@Param("newPwd") String newPwd, @Param("id") Integer id);
	
	/**
	 * 查询最后添加的安装人员信息
	 * @param 
	 * @return
	 */
	WebUser queryUserByCreateUid(String createUid);
	
	
	
	/**
	 * 查询安装人员信息
	 * @param pageBounds 分页对象
	 * @return
	 */
	PageList<WebUser>  queryUserList(WebUser user, PageBounds pageBounds);
	
	/**
	 * 根据子级额户查询父级用户ID
	 * @param username 用户名
	 * @return
	 */
	WebUser selectParentUserByChild(String username);
	
	
	WebUser selectUserByEntity(WebUser user);
	


}