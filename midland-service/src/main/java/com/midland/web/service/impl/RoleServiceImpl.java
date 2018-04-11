package com.midland.web.service.impl;

import com.midland.core.generic.GenericDao;
import com.midland.core.generic.GenericServiceImpl;
import com.midland.web.dao.RoleMapper;
import com.midland.web.dao.RolePermissionMapper;
import com.midland.web.dao.UserMapper;
import com.midland.web.model.AuthRelation;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.role.RoleAuth;
import com.midland.web.model.role.RoleExample;
import com.midland.web.model.role.RoleExample.Criteria;
import com.midland.web.model.role.RolePermission;
import com.midland.web.model.user.UserRole;
import com.midland.web.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现类
 *
 * @author
 * @since 2016年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public GenericDao<Role, Integer> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(String userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<Role> selectRoleList(Role role) {
        RoleExample example = new RoleExample();
        Criteria criteria = example.createCriteria();
        if (role != null) {
            if (StringUtils.isNotEmpty(role.getRoleName())) {
                criteria.andRoleNameEqualTo(role.getRoleName());
            }
            if (StringUtils.isNotEmpty(role.getRoleSign())) {
                criteria.andRoleSignEqualTo(role.getRoleSign());
            }
            if (role.getRoleType() != null) {
                criteria.andRoleTypeEqualTo(role.getRoleType());
            }
            if (role.getState() != null) {
                criteria.andStateEqualTo(role.getState());
            }
        }
        List<Role> list = roleMapper.selectByExample(example);
        return list;
    }


    @Override
    public int insert(Role model) {
        if (model != null) {
            model.setState(1);
        }
        return roleMapper.insertSelective(model);
    }

    @Override
    public int update(Role model) {
        return roleMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Role selectByExampleOne(Role role) {
        RoleExample example = new RoleExample();
        Criteria criteria = example.createCriteria();
        if (role != null) {
            if (StringUtils.isNotEmpty(role.getRoleName())) {
                criteria.andRoleNameEqualTo(role.getRoleName());
            }
            if (StringUtils.isNotEmpty(role.getRoleSign())) {
                criteria.andRoleSignEqualTo(role.getRoleSign());
            }
            if (role.getRoleType() != null) {
                criteria.andRoleTypeEqualTo(role.getRoleType());
            }
            if (role.getState() != null) {
                criteria.andStateEqualTo(role.getState());
            }
        }
        List<Role> list = roleMapper.selectByExample(example);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public List<Role> selectByExampleAndPage(Role role) {
        RoleExample example = new RoleExample();
        Criteria criteria = example.createCriteria();
        if (role != null) {
            if (StringUtils.isNotEmpty(role.getRoleName())) {
                criteria.andRoleNameLike("%" + role.getRoleName() + "%");
            }
            if (StringUtils.isNotEmpty(role.getRoleSign())) {
                criteria.andRoleSignLike("%" + role.getRoleSign() + "%");
            }
            if (role.getRoleType() != null) {
                criteria.andRoleTypeEqualTo(role.getRoleType());
            }
            if (role.getState() != null) {
                criteria.andStateEqualTo(role.getState());
            }
        }
        List<Role> list = roleMapper.selectByExampleAndPage(example);
        return list;
    }

    @Override
    public List<RoleAuth> getListAuthid(RoleAuth roleAuth) {
        return roleMapper.getListAuthid(roleAuth);

    }

    @Override
    public List<AuthRelation> getAuths() {
        Map<String, String> queryMap = new HashMap<String, String>(2);
        List<AuthRelation> list = roleMapper.getFatherAuths();
        for (AuthRelation auth : list) {
            queryMap.put("code", auth.getCode());
            List<AuthRelation> childList = roleMapper.getChildAuths(queryMap);
            auth.setAuthList(childList);
        }
        return list;
    }

    @Override
    public List<RolePermission> getListPermission(RolePermission rp) {
        return roleMapper.getListPermission(rp);
    }

    @Override
    public List<Permission> getPermissions() {
        Map<String, Integer> queryMap = new HashMap<String, Integer>(2);
        List<Permission> list = roleMapper.getFatherPermissions();
        for (Permission auth : list) {
            queryMap.put("parentId", auth.getId());
            List<Permission> childList = roleMapper.getChildPermissions(queryMap);
            auth.setPermissionList(childList);
        }
        return list;
    }

    @Override
    @Transactional
    public void saveRolePermissions(Integer rId, List<RolePermission> rolePermissionList) {
        rolePermissionMapper.deleteBatchByRoleId(rId);
        if (rolePermissionList != null && rolePermissionList.size() > 0) {
            rolePermissionMapper.insertBatch(rolePermissionList);//批量新增
        }
    }

//    @Override
//    public int saveRolePermissions(String roleId, String permissionIds) {
//        //角色id
//        Integer rId = Integer.valueOf(roleId);
//        //数据更改数目
//        int n = 0;
//
//        RolePermission rp = new RolePermission();
//        rp.setRoleId(rId);
//        List<RolePermission> permissionIdList = roleMapper.getListPermission(rp);//已有的权限
//
//        if (StringUtils.isNotEmpty(permissionIds)) {
//
//            String[] ids = permissionIds.split(",");
//            List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();//页面传的权限
//            RolePermission rolePermission = null;
//            for (int a = 0; a < ids.length; a++) {//数组转换成list
//                rolePermission = new RolePermission();
//                rolePermission.setRoleId(Integer.valueOf(roleId));
//                rolePermission.setPermissionId(Integer.valueOf(ids[a]));
//                rolePermissionList.add(rolePermission);
//            }
//
//            //rolePermissionList留下是要新增的
//            //permissionIdList留下是要删除的
//            for (int i = rolePermissionList.size() - 1; i >= 0; i--) {
//                Integer p1 = rolePermissionList.get(i).getPermissionId();
//                for (int j = permissionIdList.size() - 1; j >= 0; j--) {
//                    Integer p2 = permissionIdList.get(j).getPermissionId();
//                    if (p2.compareTo(p1) == 0) {//相同的去除，剩下新增和删除的
//                        permissionIdList.remove(j);
//                        rolePermissionList.remove(i);
//                        break;
//                    }
//                }
//            }
//
//            if (rolePermissionList != null && rolePermissionList.size() > 0) {
//                n = n + rolePermissionMapper.insertBatch(rolePermissionList);//批量新增
//            }
//        }
//        if (permissionIdList != null && permissionIdList.size() > 0) {
//            n = n + rolePermissionMapper.deleteBatch(rId, permissionIdList);//批量删除
//        }
//
//        return n;
//    }

    @Override
    public int updateRoleUser(Integer roleId, String userIds) {
        int m = 0;
        List<UserRole> list1 = roleMapper.findUserRoleByRoleId(roleId);//用户的角色
        if (StringUtils.isNotEmpty(userIds)) {
            UserRole userRole = null;
            List<UserRole> list2 = new ArrayList<UserRole>();//页面返回的用户角色关系
            String[] uids = userIds.split(",");
            for (int n = 0; n < uids.length; n++) {//数组转list
                userRole = new UserRole();
                userRole.setUserId(uids[n]);
                userRole.setRoleId(roleId);
                list2.add(userRole);
            }

            for (int i = list2.size() - 1; i >= 0; i--) {
                String userId2 = list2.get(i).getUserId();

                for (int j = list1.size() - 1; j >= 0; j--) {
                    String userId1 = list1.get(j).getUserId();

                    if (userId1.compareTo(userId2) == 0) {
                        list1.remove(j);
                        list2.remove(i);
                        break;
                    }
                }
            }

            if (list2 != null && list2.size() > 0) {
                m = m + userMapper.insertUserRoleBatch(list2);
            }
        }
        if (list1 != null && list1.size() > 0) {
            m = m + userMapper.deleteUserRoleBatchById(list1);
        }
        return m;
    }

    @Override
    public int modifyRole(Role role) {

        return roleMapper.updateByPrimaryKeySelective(role);
    }
}
