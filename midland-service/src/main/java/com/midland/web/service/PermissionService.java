package com.midland.web.service;

import com.midland.core.generic.GenericService;
import com.midland.web.model.Permission;

import java.util.List;

/**
 * 权限 业务接口
 *
 * @author
 * @since 2016年6月10日 下午12:02:39
 **/
public interface PermissionService extends GenericService<Permission, Integer> {

    /**
     * 通过角色id 查询角色 拥有的权限
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roleId);

}
