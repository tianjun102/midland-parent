package com.midland.web.dao;

import com.midland.core.generic.GenericDao;
import com.midland.web.model.user.User;
import com.midland.web.model.user.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户Dao接口
 *
 * @author
 * @since 2016年7月5日 上午11:49:57
 **/
@Repository
public interface UserMapper extends GenericDao<User, String> {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(User user);

    User selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录验证查询
     *
     * @param record
     * @return
     */
    User authentication(User record);

    /**
     * 分页条件查询
     *
     * @param user
     * @return
     */
    List<User> selectByExampleAndPage(User user);

    /**
     * 批量新增用户角色关系
     *
     * @param list
     * @return
     */
    int insertUserRoleBatch(List<UserRole> list);

    /**
     * 查找用户的角色关系
     *
     * @param userId
     * @return
     */
    List<UserRole> findUserRoleByUserId(@Param("userId") String userId);

    /**
     * 批量删除用户角色关系
     *
     * @return
     */
    int deleteUserRoleBatch(Map map);

    List<User> selectUsersByRoleId(Integer roleId);

    int deleteUserRoleBatchById(List<UserRole> list);

    User queryUserForPhone(String phone);

}