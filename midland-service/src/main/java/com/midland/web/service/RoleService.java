package com.midland.web.service;

import com.midland.core.generic.GenericService;
import com.midland.web.model.AuthRelation;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.role.RoleAuth;
import com.midland.web.model.role.RolePermission;

import java.util.List;

/**
 * 角色 业务接口
 * 
 * @author 
 * @since 2016年6月10日 下午4:15:01
 **/
public interface RoleService extends GenericService<Role, Integer> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);

	List<Role> selectRoleList(Role role);

	/**
	 * 分页搜索查询
	 * @param role
	 * @return
	 */
	List<Role> selectByExampleAndPage(Role role);

	/**
	 * 条件查询获取对象
	 * @param role
	 * @return
	 */
	Role selectByExampleOne(Role role);

	List<RoleAuth> getListAuthid(RoleAuth roleAuth);

	List<AuthRelation> getAuths();

	List<RolePermission> getListPermission(RolePermission rp);

	List<Permission> getPermissions();

	int saveRolePermissions(String roleId, String permissionIds);

	int updateRoleUser(Integer roleId, String userIds);
	
	int modifyRole(Role role);

}
