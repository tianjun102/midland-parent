package com.midland.web.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.core.util.HttpUtils;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.user.Agenter;
import com.midland.web.util.MidlandHelper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.user.User;
import com.midland.web.service.PermissionService;
import com.midland.web.service.RoleService;
import com.midland.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        Map<String,String> parem = new HashMap<>();
        parem.put("userName",username);
        parem.put("password",baseRedisTemplate.getValueByKey(username).toString());
        String data = HttpUtils.get("http://218.18.9.171:8183/dingjian/website/api/agenter/login", parem);
        Map userMap =  (Map)JSONObject.parse(data);
        if(("SUCCESS").equals(userMap.get("STATE"))) {
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
        Map<String,String> parem = new HashMap<>();
        parem.put("userName",username);
        parem.put("password",oldPassWord);
        String data = HttpUtils.get("http://218.18.9.171:8183/dingjian/website/api/agenter/login", parem);
        Map userMap =  (Map)JSONObject.parse(data);
        baseRedisTemplate.saveValue(username,oldPassWord);
        if(("SUCCESS").equals(userMap.get("STATE"))) {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
            return authenticationInfo;
        }
        //List<String> areaList = MidlandHelper.getPojoList(data, String.class);
        // 通过数据库进行验证
        User authentication = null;
        if(((UsernamePasswordToken)token).isRememberMe()){
        authentication = userService.authentication(new User(username, password,"1"));	
        }else{
        authentication = userService.authentication(new User(username, password));
        }
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
