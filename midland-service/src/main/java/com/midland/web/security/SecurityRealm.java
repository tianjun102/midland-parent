package com.midland.web.security;

import com.alibaba.fastjson.JSONObject;
import com.midland.config.MidlandConfig;
import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.core.util.HttpUtils;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.user.User;
import com.midland.web.service.PermissionService;
import com.midland.web.service.RoleService;
import com.midland.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户身份验证,授权 Realm 组件
 *
 * @author
 * @since 2016年6月11日 上午11:35:28
 **/
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Autowired
    private IBaseRedisTemplate baseRedisTemplate;

    @Autowired
    private MidlandConfig midlandConfig;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        Map<String, String> param = new HashMap<>();
        param.put("userName", username);
        param.put("password", baseRedisTemplate.getValueByKey(username).toString());
        String data = HttpUtils.get(midlandConfig.getAgentLogin(), param);
        Map userMap = (Map) JSONObject.parse(data);
        if (userMap != null) {
            if (("SUCCESS").equals(userMap.get("STATE"))) {
                final List<Role> roleInfos1 = roleService.selectRolesByUserId("88888");
                for (Role role1 : roleInfos1) {
                    authorizationInfo.addRole(role1.getRoleSign());

                    final List<Permission> permissions1 = permissionService.selectPermissionsByRoleId(role1.getId());
                    for (Permission permission : permissions1) {
                        // 添加权限
                        authorizationInfo.addStringPermission(permission.getPermissionSign());
                    }
                }
                return authorizationInfo;

            }
        }

        final User user = userService.selectByUsername(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (Role role : roleInfos) {
            // 添加角色
//            System.err.println(role);
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                // 添加权限
//                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String oldPassWord = SysContext.getRequest().getParameter("password");
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        Map<String, String> parem = new HashMap<>();
        parem.put("userName", username);
        parem.put("password", oldPassWord);
        String data = null;
        data = HttpUtils.get(midlandConfig.getAgentLogin(), parem);
        Map userMap = (Map) JSONObject.parse(data);
        baseRedisTemplate.saveValue(username, oldPassWord);
        if (userMap != null) {
            if (("SUCCESS").equals(userMap.get("STATE"))) {
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
                return authenticationInfo;
            }
        }
        //List<String> areaList = MidlandHelper.getPojoList(data, String.class);
        // 通过数据库进行验证
        User authentication = null;
        if (((UsernamePasswordToken) token).isRememberMe()) {
            authentication = userService.authentication(new User(username, password, "1"));
        } else {
            authentication = userService.authentication(new User(username, password));
        }
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
