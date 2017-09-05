package com.midland.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midland.core.generic.GenericDao;
import com.midland.core.generic.GenericServiceImpl;
import com.midland.web.dao.PermissionMapper;
import com.midland.web.model.Permission;
import com.midland.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author 
 * @since 2016年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Integer> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public GenericDao<Permission, Integer> getDao() {
        return permissionMapper;
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }
}
