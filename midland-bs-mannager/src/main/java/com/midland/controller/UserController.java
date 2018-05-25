package com.midland.controller;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.midland.base.BaseFilter;
import com.midland.config.MidlandConfig;
import com.midland.core.util.ApplicationUtils;
import com.midland.core.util.HttpUtils;
import com.midland.core.util.MD5Util;
import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.model.Area;
import com.midland.web.model.ExportModel;
import com.midland.web.model.HeadMsg;
import com.midland.web.model.role.Role;
import com.midland.web.model.user.Agenter;
import com.midland.web.model.user.User;
import com.midland.web.security.PermissionSign;
import com.midland.web.security.RoleSign;
import com.midland.web.security.SysContext;
import com.midland.web.service.*;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import com.midland.web.util.PoiExcelExport;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户控制器
 *
 * @author
 * @since 2016年5月28日 下午3:54:00
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "/user")
public class UserController extends BaseFilter {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;
    @Autowired
    private MenuService menuServiceImpl;
    @Autowired
    private SettingService settingService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MidlandConfig midlandConfig;



    @Autowired
    private SmsSingleSender sender;
    @Autowired
    private HeadMsgService headMsgServiceImpl;


    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = URLEncoder.encode(request.getParameter("username"), "utf-8");
        String password = request.getParameter("password");
        SysContext.setRequest((HttpServletRequest) request);
        SysContext.setResponse((HttpServletResponse) response);
        String flag = request.getParameter("remember");//记住密码
        String userType = request.getParameter("userType");
        userType = "0";
        try {

            Subject subject = SecurityUtils.getSubject();

            if (flag != null && flag.length() > 0) {
                //创建Cookie

                Cookie nameCookie = new Cookie("username", username);
                nameCookie.setPath("/");
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                Cookie passwordCookie = new Cookie("password", URLEncoder.encode(password));
                passwordCookie.setPath("/");
                passwordCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(passwordCookie);
                Cookie remenber = new Cookie("remenber",flag);
                remenber.setPath("/");
                remenber.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(remenber);

            }
            user.setPassword(password);
            user.setUsername(username);
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(),userType);
            if (flag != null && flag.equals("1")) {
                token.setRememberMe(true);
            }
            // 身份验证
            subject.login(token);
            // 验证成功在Session中保存用户信息
            Map<String, String> parem = new HashMap<>();
            parem.put("userName", username);
            parem.put("password", password);
            String data = HttpUtils.get(midlandConfig.getAgentLogin(), parem);
            Map userMap = null;
            try {
                userMap = (Map) JSONObject.parse(data);
            } catch (Exception e) {
                logger.error("请检查顶尖经纪人登录接口是否正常",e);
                e.printStackTrace();
            }
            if (userMap != null) {
                if ("SUCCESS".equals(userMap.get("STATE"))) {
                    String dataDtail = HttpUtils.get(midlandConfig.getAgentDetail() + "?id=" + "0024e866-540c-4246-b778-a0a126e8f807", null);
                    Agenter agenterList = MidlandHelper.getPojo(dataDtail);
                    if (agenterList != null) {
                        User agenterUser = new User();
                        agenterUser.setId(userMap.get("agenterId") == null ? null : userMap.get("agenterId").toString());
                        agenterUser.setPhone(agenterList.getPhone());
                        agenterUser.setUsername(username);
                        agenterUser.setPassword(password);
                        agenterUser.setUserCnName(agenterList.getName());
                        agenterUser.setCityName(agenterList.getStoreName());
                        List<Role> roles = roleService.selectRolesByUserId("88888");
                        agenterUser.setRoles(roles);
                        request.getSession().setAttribute("userInfo", agenterUser);
                        return "redirect:/";
                    }
                }
            }
            final User authUserInfo = userService.selectByUsername(user.getUsername());

            List<Role> roles = roleService.selectRolesByUserId(authUserInfo.getId());
            for (Role role : roles) {
                if (role.getRoleType() != null && role.getRoleType() == 0) {
                    //超级管理员
                    authUserInfo.setIsSuper("1");
                }
            }
            authUserInfo.setRoles(roles);
            request.getSession().setAttribute("userInfo", authUserInfo);
            //request.getSession().setMaxInactiveInterval(10);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误!");

            e.printStackTrace();
            return "login";

        }

        return "redirect:/";
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(HttpSession session) {
        Map map = new HashMap();
        try {
            session.removeAttribute("userInfo");
            // 登出操作
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            map.put("state",0);
            map.put("message","success");
        } catch (Exception e) {
            map.put("state",-1);
            map.put("message","service error");
        }

        return map;
    }

    @RequestMapping(value = "/loginIndex", method = RequestMethod.GET)
    public String logout() {

        return "/page/login";
    }


    /**
     * 头部
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/head", method = {RequestMethod.GET, RequestMethod.POST})
    public String head(Model model, HttpServletRequest request) {
        User user = MidlandHelper.getCurrentUser(request);

        HeadMsg headMsg = new HeadMsg();
        List list = null;
        try {
            headMsg.setIsDelete(Contant.isNotDelete);
            headMsg.setIsShow(Contant.isShow);
            list = headMsgServiceImpl.findHeadMsgList(headMsg);
        } catch (Exception e) {
            logger.error("head", e);
        }
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "head";
    }

    /**
     * 左边菜单栏
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/left", method = {RequestMethod.GET, RequestMethod.POST})
    public String left(Model model, HttpSession session) throws Exception {
        return "left";
    }

    //*******************************前端用户**********************************************************

    /**
     * 进入用户列表搜索页面
     *
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bsUserIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String bsUserIndex(User user, Model model, HttpServletRequest request) {
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        return "user/bsUserIndex";
    }


    /**
     * 查询前台用户
     *
     * @return
     */
    @RequestMapping(value = "/bsUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String userInfo(String userId, Model model, HttpServletRequest request) {
        User userInfo = userService.selectById(userId);
        model.addAttribute("user", userInfo);
        Role role = new Role();
        role.setState(1);
        List<Role> roles = roleService.selectRoleList(role);//所有角色
        List<Role> userRoles = roleService.selectRolesByUserId(userId);//用户的角色
        model.addAttribute("roles", roles);
        model.addAttribute("userRoles", userRoles);
        return "user/bsUserInfo";
    }

    /**
     * 前台用户列表查询
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/bsUserList", method = {RequestMethod.GET, RequestMethod.POST})
    public String bsUserList(User user, Model model, HttpServletRequest request) {
        User currUser = MidlandHelper.getCurrentUser(request);
        if (StringUtils.isEmpty(currUser.getIsSuper())) {
            if (StringUtils.isEmpty(user.getCityId())) {
                user.setCityId(currUser.getCityId());
            }
        }
        getUserList(user, model, request);
        List<ParamObject> map = JsonMapReader.getMap("audit_status");
        model.addAttribute("auditSatusList", map);
        return "user/bsUserlist";
    }



    //********************************后台用户**********************************************************


    /**
     * 进入用户列表搜索页面
     *
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/userIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String findUserIndex(User user, Model model, HttpServletRequest request) {
        List<ParamObject> sources = JsonMapReader.getMap("source");
        User user1 = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user1.getIsSuper());
        model.addAttribute("sources", sources);
        return "user/userIndex";
    }

    /**
     * 用户列表查询
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/userList", method = {RequestMethod.GET, RequestMethod.POST})
    public String selectUserList(User user, Model model, HttpServletRequest request) {
        User currUser = MidlandHelper.getCurrentUser(request);
        if (!Contant.isSuper.equals(currUser.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
            if (StringUtils.isEmpty(user.getCityId())) {
                user.setCityId(currUser.getCityId());
            }
        }
        if (user.getUserType() == null) {
            user.setUserType(-1);
        }
        getUserList(user, model, request);
        List<ParamObject> map = JsonMapReader.getMap("audit_status");
        model.addAttribute("auditSatusList", map);
        return "user/userlist";
    }


    public void getUserList(User user, Model model, HttpServletRequest request) {
        MidlandHelper.doPage(request);
        Page<User> userList = (Page<User>) userService.selectByExampleAndPage(user);
        Paginator paginator = userList.getPaginator();
        model.addAttribute("paginator", paginator);
        model.addAttribute("users", userList);
    }


    /**
     * 跳转到新增页面
     *
     * @return
     */
    @RequestMapping(value = "/toAddPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAddPage(Model model, HttpServletRequest request) {
        Role role = new Role();
        role.setState(1);
        List<Role> roles = roleService.selectRoleList(role);
        List<Area> list = settingService.queryAllCityByRedis();
        settingService.getAllProvinceList(model);
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        model.addAttribute("citys", list);
        model.addAttribute("roles", roles);
        return "user/addUser";
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Object addUser(User user) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.addUser(user);
            map.put("state", 0);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                logger.error("addUser :{}{}", user, e.getMessage());
                map.put("state", 1);
            }else {
                logger.error("addUser :{}", user, e);
                map.put("state",-1);
            }
        }

        return map;
    }

    /**
     * 查询用户
     *
     * @param flag 0不传值，1代表打开查看实名信息
     * @return
     */
    @RequestMapping(value = "/findUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String findUser(String userId, Integer flag, Model model, HttpServletRequest request) {
        User userInfo = userService.selectById(userId);
        model.addAttribute("user", userInfo);
        if (flag == 1) {
            return "user/viewRegistration";
        }
        Role role = new Role();
        role.setState(1);
        List<Role> roles = roleService.selectRoleList(role);//所有角色
        List<Role> userRoles = roleService.selectRolesByUserId(userId);//用户的角色
        model.addAttribute("roles", roles);
        model.addAttribute("userRoles", userRoles);
        return "user/userInfo";
    }


    /**
     * 用户的角色列表展示
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/userRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String userRoleList(String userId, Model model, HttpServletRequest request) {
        User userInfo = userService.selectById(userId);
        Role role = new Role();
        role.setState(1);
        List<Role> roles = roleService.selectRoleList(role);//所有角色
        List<Role> userRoles = roleService.selectRolesByUserId(userId);//用户的角色
        model.addAttribute("user", userInfo);
        model.addAttribute("roles", roles);
        model.addAttribute("userRoles", userRoles);
        return "user/userRoleList";
    }

    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/saveUserRole", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object saveUserRole(String userId, String roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (userService.updateUserRole(userId, roleIds) > 0) {
            map.put("flag", 1);
        }
        return map;
    }

    /**
     * 跳转到修改页面
     *
     * @return
     */
    @RequestMapping(value = "/toUpdatePage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdatePage(Model model, String userId, HttpServletRequest request) {
        settingService.getAllProvinceList(model);
        User userInfo = userService.selectById(userId);
        model.addAttribute("item", userInfo);
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        return "user/updateUser";
    }

    /**
     * 跳转到修改页面
     *
     * @return
     */
    @RequestMapping(value = "/hrefUpdateUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String hrefUpdateUserPage(Model model, String userId, HttpServletRequest request) {
        User userInfo = userService.selectById(userId);
        model.addAttribute("item", userInfo);
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        return "user/hrefUpdateUser";
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object updateUser(User user, int isFlag, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (isFlag == 1) {
            user.setUsername(null);
            user.setPhone(null);

        }
        if (StringUtils.isEmpty(user.getEmail())) {
            user.setEmail("");
        }
        if (userService.update(user) > 0) {
            if (StringUtils.isNotEmpty(user.getHeadImg())) {
                User user1 = MidlandHelper.getCurrentUser(request);
                user1.setHeadImg(user.getHeadImg());
                request.getSession().setAttribute("userInfo", user1);
            }
            map.put("state", 0);
            map.put("message", "success");
            return map;
        }
        map.put("state", -1);
        map.put("message", "fail");
        return map;
    }

    /**
     * 用户审核
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/audit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object auditUser(User user, HttpServletRequest request) {
        User currUser = (User) request.getSession().getAttribute("userInfo");
        Map<String, Object> map = new HashMap<>();
        user.setAuditTime(MidlandHelper.getCurrentTime());
        user.setAuditName(currUser.getUsername());
        if (userService.modifyUser(user) > 0) {
            map.put("state", 0);
            map.put("message", "success");
            return map;
        }
        map.put("state", -1);
        map.put("message", "fail");
        return map;
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateUserInfo(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (userService.modifyUser(user) > 0) {
                map.put("state", 0);
                map.put("message", "success");
                return map;
            }
            map.put("state", -1);
            map.put("message", "fail");

        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                logger.error("update :{}{}", user, e.getMessage());
                map.put("state", 1);
            }else {
                logger.error("update :{}", user, e);
                map.put("state",-1);
            }

        }
        return map;
    }


    /**
     * 开启/关闭
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/offOn", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String offOn(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (userService.update(user) > 0) {
            map.put("flag", 1);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String deleteUser(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        User user = new User();
        user.setId(userId);
        user.setState("3");//删除
        int n = userService.update(user);
        if (n > 0) {
            map.put("flag", 1);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 检查用户名唯一性
     *
     * @return
     */
    @RequestMapping(value = "/checkUnique", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String checkUserNameUnique(String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (userName != null && userService.selectByUsername(userName) != null) {
            map.put("flag", 1);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 检查手机号唯一性
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkPhoneUnique", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String checkPhoneUnique(String phone) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        User user = new User();
        user.setPhone(phone);
        List<User> list = userService.selectUserList(user);
        if (list != null && list.size() > 0) {
            map.put("flag", 1);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 跳转到修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/forcedModifyPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public String toForcedModifyPassword() {

        return "user/forcedModifyPassword";
    }

    /**
     * 跳转到修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/toModifyPwdPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toModifyPwdPage() {

        return "user/modifyPassword";
    }

    /**
     * 检查原密码是否正确
     *
     * @param oldPwd
     * @param request
     * @return 1 正确  0 错误
     */
    @RequestMapping(value = "/checkOldPwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object checkPwd(String oldPwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (oldPwd != null && user != null) {
            if (ApplicationUtils.sha256Hex(oldPwd).equalsIgnoreCase(user.getPassword())) {
                map.put("flag", 1);
            }
        }
        return map;
    }

    /**
     * 修改密码提交保存
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object updatePwd(HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        String newPwd = request.getParameter("newPwd");
        User sessionUser = (User) request.getSession().getAttribute("userInfo");
        User user = new User();
        user.setId(sessionUser.getId());
        user.setPassword(newPwd);
        int n = userService.update(user);
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if (n > 0) {
            map.put("flag", 1);
        }
        return map;
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String resetPassword(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            user.setPassword(Contant.DEFAULT_PASSWORD);
            if (userService.update(user) > 0) {
                map.put("flag", 1);
            }
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 找回密码发送邮件
     *
     * @return
     */
    @RequestMapping(value = "/sendMail", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendMail(HttpServletRequest request, String email, String un) {
        String secretKey = UUID.randomUUID().toString(); // 密钥
        Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);//过期时间： 30分钟后过期
        long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的

        User user = userService.selectByUsername(un);
        if (user != null) {
//    		user.set
            //更新数据库
            //userService.update(user);

        }


        String key = un + "$" + date + "$" + secretKey;
        String digitalSignature = MD5Util.MD5Encode(key, null);// 数字签名
        String basePath = request.getRequestURL().toString();
        //http://localhost:8080/webmvc/rest/user/sendMail
        basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
        String resetPassHref = basePath + "checkLink?sid=" + digitalSignature + "&userName=" + un;
        StringBuffer sb = new StringBuffer();
        sb.append("请勿回复本邮件.点击下面的链接,重设密码<br/><a href=")
                .append(resetPassHref).append(" target='_BLANK'>")
                .append(resetPassHref).append("</a>  或者    <a href=")
                .append(resetPassHref).append(" target='_BLANK'>点击我重新设置密码</a>")
                .append("<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'")
                .append("\t").append(digitalSignature);


        SmsUtil.send("13602825350", "xcv12345678");

        return "login";
    }

    /**
     * 验证密码找回链接
     *
     * @return
     */
    @RequestMapping(value = "/checkLink", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkResetLink(Model model, HttpServletRequest request) {
        String sid = request.getParameter("sid");
        String userName = request.getParameter("userName");

        if (sid.equals("") || userName.equals("")) {
            model.addAttribute("mesg", "链接不完整,请重新生成");
            return "error";
        }

        User user = userService.selectByUsername(userName);
        if (user != null) {
            Timestamp outDate = new Timestamp(1111);//数据库用户过期时间

            if (outDate.getTime() <= System.currentTimeMillis()) { //表示已经过期
                model.addAttribute("mesg", "链接已经过期,请重新申请找回密码.");
                return "error";
            }

            String key = user.getUsername() + "$" + outDate.getTime() / 1000 * 1000 + "$" + "user.getValidataCode()";//数字签名
            String digitalSignature = MD5Util.MD5Encode(key, null);// 数字签名

            if (!digitalSignature.equals(sid)) {
                model.addAttribute("mesg", "链接不正确,是否已经过期了?重新申请吧.");
                return "error";
            } else {
                //链接验证通过 转到修改密码页面
                model.addAttribute("user", user);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    /**
     * 发送短信
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/sendSms", produces = "application/json; charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendSms(String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);

        String vcode = SmsUtil.createRandomVCode();//验证码
        String mobile = "";
        String key = "wks:vcode:" + username;
        String content = "";
        User user = userService.selectByUsername(username);
        if (user != null) {
            mobile = user.getPhone();
            content = " 您正在重置密码,验证码:" + vcode + ",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人。";
            if (mobile != null && mobile.length() > 0) {
                if (SmsUtil.send(mobile, content)) {
                    ValueOperations<String, Object> vo = redisTemplate.opsForValue();
                    vo.set(key, vcode);
                    redisTemplate.expire(key, 15, TimeUnit.MINUTES);//15分钟过期
                    map.put("flag", 1);
                    map.put("msg", "发送成功!");
                } else {
                    map.put("msg", "短信发送失败，请稍后再试!");
                }
            } else {
                map.put("msg", "该用户名未绑定有效的手机号码!");
            }
        } else {
            map.put("msg", "无效的用户名!");
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 验证码校验
     *
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/checkVcode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String checkVcode(String username, String vcode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        String key = "wks:vcode:" + username;
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        String redisVcode = vo.get(key).toString();
        if (redisVcode.equals(vcode)) {
            map.put("flag", 1);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 中间首页图
     *
     * @return
     */
    @RequestMapping(value = "/contentIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String contentIndex() {

        return "contentIndex";
    }

    /**
     * 跳转到关于页面
     *
     * @return
     */
    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String about() {

        return "about";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }


    @RequestMapping("/export")
    public void userInfoExportExcel(User user, HttpServletResponse response, HttpServletRequest request) {
        User currUser = MidlandHelper.getCurrentUser(request);
        if (StringUtils.isEmpty(user.getCityId())) {
            user.setCityId(currUser.getCityId());
        }
        List<User> dataList = userService.selectUserList(user);
        List<ExportModel> exportModels = new ArrayList<>();


        for (User user1 : dataList) {
            ExportModel exportModel = new ExportModel();
            exportModel.setModelName1(user1.getUsername());
            exportModel.setModelName2(user1.getUserCnName());
            exportModel.setModelName3(user1.getPhone());
            exportModel.setModelName4(user1.getCreateTime());
            List<ParamObject> sources = JsonMapReader.getMap("source");
            exportModel.setModelName5(MidlandHelper.getNameById(user1.getSource(), sources));
            List<ParamObject> auditSatus = JsonMapReader.getMap("audit_status");
            exportModel.setModelName6(MidlandHelper.getNameById(user1.getAuditStatus(), auditSatus));
            exportModel.setModelName7(user1.getAuditName());
            exportModel.setModelName8(user1.getAuditTime());


            exportModels.add(exportModel);
        }
        PoiExcelExport pee = new PoiExcelExport(response, "用户", "sheet1");
        //调用
        String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5", "modelName6", "modelName7", "modelName8"};
        String titleName[] = {"用户名", "昵称", "手机号码", "注册时间", "平台", "实名状态", "审核人", "审核时间"};
        int titleSize[] = {13, 13, 13, 13, 13, 13, 13, 13};
        //其他设置 set方法可全不调用
        pee.wirteExcel(titleColumn, titleName, titleSize, exportModels, request);
    }



    @RequestMapping(value = "/vcode/toVcode", method = {RequestMethod.GET, RequestMethod.POST})
    public String toVcode(User user, Model model, HttpServletRequest request) {
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        return "user/toVcode";
    }


    /**
     * 发送短信
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/vcode/sendSms")
    @ResponseBody
    public Map vcodeSendSms(String phone) {
        User user = new User();
        user.setPhone(phone);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (StringUtils.isEmpty(phone)) {
            map.put("msg", "手机号不能为空！");
            return map;
        }
        String vcode = SmsUtil.createRandomVCode();//验证码
        String mobile = "";
        String key = "midland:vcode:" + phone;
        user = userService.selectByUser(user);
        if (user != null) {
            mobile = user.getPhone();
            if (mobile != null && mobile.length() > 0) {
                List list = new ArrayList();
                list.add(vcode);
                list.add("5");
                SmsModel smsModel = new SmsModel(mobile, "54711", list);
                ValueOperations<String, Object> vo = redisTemplate.opsForValue();
                vo.set(key, vcode);
                redisTemplate.expire(key, 5, TimeUnit.MINUTES);//15分钟过期
                try {
                    SmsSingleSenderResult result = sender.sendWithParam("86", smsModel.getPhones(), Integer.valueOf(smsModel.getTpId()), (ArrayList<String>) smsModel.getFieldsList(), "", "", "");

                    if (result.errMsg.equals("OK")) {
                        map.put("flag", 1);
                        map.put("id", user.getId());
                        map.put("msg", "发送成功!");
                    } else {
                        map.put("id", user.getId());
                        map.put("msg", "短信发送失败，请稍后再试!");
                    }
                } catch (Exception e) {
                    map.put("id", user.getId());
                    map.put("msg", "短信发送失败，请稍后再试!");
                }

            } else {
                map.put("msg", "该用户名未绑定有效的手机号码!");
            }
        } else {
            map.put("msg", "无效的手机号!");
        }
        return map;
    }


    /**
     * 验证码校验
     *
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/vcode/checkVcode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object checkVcode_(String phone, String vcode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        String key = "midland:vcode:" + phone;
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        String redisVcode = vo.get(key).toString();
        if (redisVcode.equals(vcode)) {
            map.put("flag", 1);
        }
        return map;
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/vcode/resetPwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object resetPassword_(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            user.setPassword(Contant.DEFAULT_PASSWORD);
            if (userService.update(user) > 0) {
                map.put("flag", 1);
            }
        }
        return map;
    }

    /**
     * 修改密码提交保存
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/vcode/updatePwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object updatePwd_(HttpServletRequest request, String newPwd, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", 0);
        User user = new User();
        user.setPassword(newPwd);
        user.setId(id);
        int n = userService.update(user);
        if (n > 0) {
            map.put("flag", 1);
        }
        return map;
    }


}
