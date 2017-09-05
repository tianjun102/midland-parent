package com.midland.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.midland.core.generic.GenericDao;
import com.midland.web.model.AuthRelation;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.role.RoleAuth;
import com.midland.web.model.role.RoleExample;
import com.midland.web.model.role.RolePermission;
import com.midland.web.model.user.UserRole;

/**
 * 角色Dao 接口
 * 
 * @author 
 * @since 2016年7月5日 上午11:55:59
 **/
public interface RoleMapper extends GenericDao<Role, Integer> {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);
    
    /**
     * 分页条件查询
     * 
     * @param example
     * @return
     */
    List<Role> selectByExampleAndPage(RoleExample example);

	List<RoleAuth> getListAuthid(RoleAuth roleAuth);

	List<AuthRelation> getFatherAuths();

	List<AuthRelation> getChildAuths(Map<String, String> queryMap);

	List<RolePermission> getListPermission(RolePermission rp);

	List<Permission> getFatherPermissions();

	List<Permission> getChildPermissions(Map<String, Integer> queryMap);

	List<UserRole> findUserRoleByRoleId(@Param("roleId") Integer roleId);
}