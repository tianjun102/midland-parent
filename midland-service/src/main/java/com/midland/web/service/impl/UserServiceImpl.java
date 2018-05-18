package com.midland.web.service.impl;

import com.midland.core.generic.GenericDao;
import com.midland.core.generic.GenericServiceImpl;
import com.midland.core.util.ApplicationUtils;
import com.midland.web.Contants.Contant;
import com.midland.web.dao.UserMapper;
import com.midland.web.model.user.User;
import com.midland.web.model.user.UserRole;
import com.midland.web.service.UserService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户Service实现类
 *
 * @author
 * @since 2016年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

    @Override
    public int update(User model) {
        if (StringUtils.isNotEmpty(model.getPassword())){
            model.setPassword(ApplicationUtils.sha256Hex(model.getPassword()));
        }
        return userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, String> getDao() {
        return userMapper;
    }

    @Override
    public User selectByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> list = userMapper.selectByExample(user);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            user.setPassword(username);
            list = userMapper.selectByExample(user);
            if (list != null && list.size() > 0) return list.get(0);
        }
        return null;
    }

    @Override
    public User selectByUser(User user) {
        User list = userMapper.queryUserForPhone(user.getPhone());
        return list;
    }

    @Override
    public List<User> selectUserList(User user) {
        List<User> list = userMapper.selectByExample(user);
        return list;
    }

    @Override
    public List<User> selectByExampleAndPage(User user) {
        final List<User> list = userMapper.selectByExampleAndPage(user);
        return list;
    }

    @Override
    public void addUser(User user) throws Exception {
        if (user != null) {
            user.setCreateTime(MidlandHelper.getCurrentTime());
            user.setState("1");
            if (user.getUserType() == null) {
                user.setUserType(0);//默认为0  后台
            }
            user.setPassword(ApplicationUtils.sha256Hex(Contant.DEFAULT_PASSWORD));
            int n = userMapper.insertSelective(user);//新增返回主键id值
            if (n < 1) {
                throw new Exception("新增用户失败");
            }
            this.insertUserRoleBatch(user.getId(), user.getUserRoles());
        }
    }

    /**
     * 批量新增用户角色关系
     *
     * @param userId
     * @param userRoles
     * @return
     */
    private int insertUserRoleBatch(String userId, String userRoles) {
        if (StringUtils.isNotEmpty(userRoles)) {
            List<UserRole> list = new ArrayList<UserRole>();
            String[] roleIds = userRoles.split(",");
            Arrays.asList(userRoles.split(",")).forEach((id) -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.valueOf(id));
                list.add(userRole);
            });

            return userMapper.insertUserRoleBatch(list);//批量新增
        }
        return 0;
    }

    @Override
    public int modifyUser(User user) {
        int n = userMapper.updateByPrimaryKeySelective(user);
        this.updateUserRole(user.getId(), user.getUserRoles());
        return n;
    }

    @Override
    public int updateUserRole(String userId, String userRoles) {
        int m = 0;
        List<UserRole> list1 = userMapper.findUserRoleByUserId(userId);//已拥有的角色关系

        if (StringUtils.isNotEmpty(userRoles)) {
            UserRole userRole = null;
            List<UserRole> list2 = new ArrayList<UserRole>();//页面返回的角色关系
            String[] roleIds = userRoles.split(",");
            for (int n = 0; n < roleIds.length; n++) {//数组转list
                userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.valueOf(roleIds[n]));
                list2.add(userRole);
            }

            for (int i = list2.size() - 1; i >= 0; i--) {
                Integer roleId2 = list2.get(i).getRoleId();

                for (int j = list1.size() - 1; j >= 0; j--) {
                    Integer roleId1 = list1.get(j).getRoleId();

                    if (roleId1.compareTo(roleId2) == 0) {
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
            Map map = new HashMap<>();
            map.put("userId", userId);
            map.put("list", list1);
            m = m + userMapper.deleteUserRoleBatch(map);
        }
        return m;
    }

    @Override
    public List<User> selectUsersByRoleId(Integer roleId) {
        return userMapper.selectUsersByRoleId(roleId);
    }

}
