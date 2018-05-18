package com.midland.web.security;

import com.alibaba.fastjson.JSONObject;
import com.midland.config.MidlandConfig;
import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.core.util.ApplicationUtils;
import com.midland.core.util.HttpUtils;
import com.midland.web.Contants.Contant;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(SecurityRealm.class);
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
        final List<Role> roles = roleService.selectRolesByUserId(String.valueOf(principals.getPrimaryPrincipal()));
        return getAuthorizationInfo(authorizationInfo, roles);
    }

    private AuthorizationInfo getAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, List<Role> roles) {
        for (Role role1 : roles) {
            authorizationInfo.addRole(role1.getRoleSign());

            List<Permission> permissions1 = permissionService.selectPermissionsByRoleId(role1.getId());
            for (Permission permission : permissions1) {
                // 添加权限
                authorizationInfo.addStringPermission(permission.getPermissionSign());

            }
            if ("admin".equals(role1.getRoleSign())){
                authorizationInfo.addStringPermission("systemManage");
                authorizationInfo.addStringPermission("rolelist");
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt=(UsernamePasswordToken)token;
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());

        if ("1".equals(upt.getHost())){//经纪人
            return getAuthenticationInfo(password, username, password);
        }else if ("0".equals(upt.getHost())){//后台用户
            return getAuthenticationInfo((UsernamePasswordToken) token, username, password);
        }else{
            throw new AuthenticationException("用户名或密码错误.");
        }

    }

    private AuthenticationInfo getAuthenticationInfo(UsernamePasswordToken token, String username, String password) {
        //List<String> areaList = MidlandHelper.getPojoList(data, String.class);
        // 通过数据库进行验证
        User authentication = null;
        String sha256Hex_password= ApplicationUtils.sha256Hex(password);
        if (token.isRememberMe()) {
            authentication = userService.authentication(new User(username, sha256Hex_password, "1"));
        } else {
            authentication = userService.authentication(new User(username, sha256Hex_password));
        }
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authentication.getId(), password, getName());
        return authenticationInfo;
    }

    private AuthenticationInfo getAuthenticationInfo(String oldPassWord, String username, String password) {
        String data = null;
        Map<String, String> parem = new HashMap<>();
        parem.put("userName", username);
        parem.put("password", oldPassWord);
        data = HttpUtils.get(midlandConfig.getAgentLogin(), parem);
        Map userMap = null;
        try {
            userMap = (Map) JSONObject.parse(data);
        } catch (Exception e) {
            logger.error("请检查顶尖经纪人登录接口是否正常",e);
            e.printStackTrace();
        }
        if (userMap != null) {
            if (("SUCCESS").equals(userMap.get("STATE"))) {
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
                return authenticationInfo;
            }
        }
        //经纪人接口找不到信息就抛出异常
        throw new AuthenticationException("用户名或密码错误.");
    }

}
