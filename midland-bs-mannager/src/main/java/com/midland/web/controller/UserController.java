package com.midland.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.core.util.AppSetting;
import com.midland.core.util.ApplicationUtils;
import com.midland.core.util.MD5Util;
import com.midland.core.util.SmsUtil;
import com.midland.web.controller.base.BaseController;
import com.midland.web.model.role.Role;
import com.midland.web.model.user.User;
import com.midland.web.security.PermissionSign;
import com.midland.web.security.RoleSign;
import com.midland.web.service.MenuService;
import com.midland.web.service.RoleService;
import com.midland.web.service.UserService;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.PoiExcelExport;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户控制器
 * 
 * @author 
 * @since 2016年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    
    @Resource
	private RoleService roleService;
	@Autowired
	private MenuService menuServiceImpl;
    
    @Resource
	private RedisTemplate<String, Object> redisTemplate;

    private String loginurl = AppSetting.getAppSetting("loginurl");
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
    	String username=URLEncoder.encode(request.getParameter("username"),"utf-8");
    	String password=request.getParameter("password");
    	 String flag=request.getParameter("remember");//记住密码
    	 String userType = request.getParameter("userType");
        try {
        	
            Subject subject = SecurityUtils.getSubject();
           
            if(flag!=null && flag.length()>0){
            	//创建Cookie  
                           
                Cookie nameCookie=new Cookie("username",username); 
                nameCookie.setPath(request.getContextPath());
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                Cookie passwordCookie=new Cookie("password",password);
                passwordCookie.setPath(request.getContextPath());
                passwordCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(passwordCookie);
                    
            }
            user.setPassword(ApplicationUtils.sha256Hex(password));
            
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            if (result.hasErrors()) {
                //model.addAttribute("error", "参数错误！");
                return "redirect:"+loginurl+"?errorCode=2";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            if(userType!=null&&userType.equals("1")){
            	token.setRememberMe(true);
            }
            // 身份验证
            subject.login(token);
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            
            List<Role> roles=roleService.selectRolesByUserId(authUserInfo.getId());
            authUserInfo.setRoles(roles);
            request.getSession().setAttribute("userInfo", authUserInfo);
        } catch (AuthenticationException e) {
            // 身份验证失败
            //model.addAttribute("error", "用户名或密码错误!");
        	
        	e.printStackTrace();
            return "redirect:"+loginurl+"?errorCode=1";
        }
        if(user.getPassword().equals("92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e")){
        	return "redirect:/rest/user/forcedModifyPassword";
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
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:"+loginurl;
    }
    
    /**
     * 头部
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/head", method = {RequestMethod.GET,RequestMethod.POST})
    public String head(Model model,HttpSession session) {
//    	HttpSession session = request.getSession();
    	User user=(User) session.getAttribute("userInfo");
    	
    	/*Notice notice=new Notice();
    	notice.setMsgType(1);//智者汇 看 系统公告
    	if(user!=null && user.getUserType().compareTo(1)==0){
    		notice.setMsgType(2);//渠道商看 应用通知
    	}
    	notice.setIsSend(1);
    	notice.setIsDelete(1);
    	List<NoticeWithBLOBs> list=noticeService.selectNoticeList(notice);*/
    	
    	model.addAttribute("user",user);
    	model.addAttribute("list",null);
        return "head";
    }
    
    /**
     * 左边菜单栏
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/left", method = {RequestMethod.GET,RequestMethod.POST})
    public String left(Model model,HttpSession session) throws Exception {
//    	//父菜单
//    	List<Menu> menusTemp = new ArrayList<>();
//    	List<Menu> menus = new ArrayList<>();
//
//	    List<Menu> menuRootList = menuServiceImpl.findMenuList(new Menu());
//	    for (Menu temp : menuRootList){
//		    if (temp.getParentId() == 0){
//			    menusTemp.add(temp);
//		    }
//	    }
//
//	    for (Menu temp : menusTemp){
//	    	List<Menu> menuTemp = new ArrayList<>();
//	    	//遍历子菜单
//	        for (Menu menu : menuRootList){
//	            if (menu.getParentId() == temp.getId()){
//				    menuTemp.add(menu);
//			    }
//		    }
//		    temp.setMenuChild(menuTemp);
//		    menus.add(temp);
//	    }
//	    model.addAttribute("items",menus);
        return "left";
    }
    /**
     * 进入用户列表搜索页面
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/userIndex", method = {RequestMethod.GET,RequestMethod.POST})
    public String findUserIndex(User user, Model model, HttpServletRequest request){
    	return "user/userIndex";
    }
    /**
     * 用户列表查询 
     * @param user
     * @return
     */
    @RequestMapping(value = "/userList", method = {RequestMethod.GET,RequestMethod.POST})
    public String selectUserList(User user, Model model, HttpServletRequest request){
	    getUserList(user,model, request);
    	return "user/userlist";
    }
	
	
	
	
	public void getUserList(User user, Model model, HttpServletRequest request) {
		MidlandHelper.doPage(request);
		Page<User> userList=(Page<User>)userService.selectByExampleAndPage(user);
		Paginator paginator = userList.getPaginator();
		model.addAttribute("paginator", paginator);
		model.addAttribute("users", userList);
	}
	
	
	
	
	/**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping(value = "/toAddPage", method = {RequestMethod.GET,RequestMethod.POST})
    public String toAddPage(Model model,HttpServletRequest request){
    	Role role=new Role();
    	role.setState(1);
    	List<Role> roles = roleService.selectRoleList(role);
    	model.addAttribute("roles", roles);
    	return "user/addUser";
    }
	
	
    
    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String addUser(User user){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	user.setUserType(0);
    	if(userService.addUser(user)>0){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }

    /**
     * 查询用户
     * @param flag 0不传值，1代表打开查看实名信息
     * @return
     */
    @RequestMapping(value = "/findUser", method = {RequestMethod.GET,RequestMethod.POST})
    public String findUser(Integer userId,Integer flag,Model model,HttpServletRequest request){
    	User userInfo = userService.selectById(userId);
	    model.addAttribute("user",userInfo);
	    if(flag == 1){
		    return "user/viewRegistration";
	    }
    	Role role=new Role();
    	role.setState(1);
    	List<Role> roles=roleService.selectRoleList(role);//所有角色
    	List<Role> userRoles= roleService.selectRolesByUserId(userId);//用户的角色
    	model.addAttribute("roles",roles);
    	model.addAttribute("userRoles",userRoles);
    	return "user/userInfo";
    }
    
    
    /**
     * 用户的角色列表展示
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/userRole", method = {RequestMethod.GET,RequestMethod.POST})
    public String  userRoleList(Integer userId,Model model,HttpServletRequest request){
    	User userInfo = userService.selectById(userId);
    	Role role=new Role();
    	role.setState(1);
    	List<Role> roles=roleService.selectRoleList(role);//所有角色
    	List<Role> userRoles= roleService.selectRolesByUserId(userId);//用户的角色
    	model.addAttribute("user",userInfo);
    	model.addAttribute("roles",roles);
    	model.addAttribute("userRoles",userRoles);
    	return "user/userRoleList";
    }
    
    /**
     * 保存用户角色关系
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/saveUserRole", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object saveUserRole(Integer userId,String roleIds){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(userService.updateUserRole(userId,roleIds)>0){
    		map.put("flag", 1);
    	}
    	return map;
    }
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value = "/toUpdatePage", method = {RequestMethod.GET,RequestMethod.POST})
	public String toUpdatePage(Model model,int userId,HttpServletRequest request){
		User userInfo = userService.selectById(userId);
		model.addAttribute("user", userInfo);
		return "user/updateUser";
	}
    
    
    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateUser(User user, int isFlag, HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>();
    	if (isFlag ==1){
		    user.setUsername(null);
		    user.setPhone(null);
		
	    }
    	if(userService.modifyUser(user)>0){
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
     * @param user
     * @return
     */
    @RequestMapping(value = "/audit", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object auditUser(User user, HttpServletRequest request){
    	User currUser = (User)request.getSession().getAttribute("userInfo");
    	Map<String, Object> map = new HashMap<>();
    	user.setAuditTime(MidlandHelper.getCurrentTime());
    	user.setAuditName(currUser.getUsername());
	    if(userService.modifyUser(user)>0){
		    map.put("state", 0);
		    map.put("message","success");
		    return map;
	    }
	    map.put("state", -1);
	    map.put("message","fail");
	    return map;
    }
    
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateUserInfo(User user){
    	Map<String, Object> map = new HashMap<>();
    	if(userService.modifyUser(user)>0){
    		map.put("state", 0);
    		map.put("message","success");
    		return map;
    	}
	    map.put("state", -1);
	    map.put("message","fail");
    	return map;
    }
    
    
    
    
    /**
     * 开启/关闭
     * @param user
     * @return
     */
    @RequestMapping(value = "/offOn", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String offOn(User user){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(userService.update(user)>0){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 删除
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String deleteUser(Integer userId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	User user=new User();
    	user.setId(userId);
    	user.setState("3");//删除
    	int n=userService.update(user);
    	if(n>0){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 检查用户名唯一性
     * @return
     */
    @RequestMapping(value = "/checkUnique", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String checkUserNameUnique(String userName){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(userName!=null && userService.selectByUsername(userName)!=null){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 检查手机号唯一性
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkPhoneUnique", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String checkPhoneUnique(String phone){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	User user = new User();
    	user.setPhone(phone);
    	List<User> list = userService.selectUserList(user);
    	if(list!=null&& list.size()>0){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 跳转到修改密码页
     * @return
     */
    @RequestMapping(value = "/forcedModifyPassword", method = {RequestMethod.GET,RequestMethod.POST})
    public String toForcedModifyPassword(){
    	
    	return "user/forcedModifyPassword";
    }
    
    /**
     * 跳转到修改密码页
     * @return
     */
    @RequestMapping(value = "/toModifyPwdPage", method = {RequestMethod.GET,RequestMethod.POST})
    public String toModifyPwdPage(){
    	
    	return "user/modifyPassword";
    }
    
    /**
     * 检查原密码是否正确 
     * @param oldPwd
     * @param request
     * @return 1 正确  0 错误
     */
    @RequestMapping(value = "/checkOldPwd", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object checkPwd(String oldPwd,HttpServletRequest request){
    	 HttpSession session = request.getSession();
    	User user=(User) session.getAttribute("userInfo");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(oldPwd!=null && user!=null){
    		if(ApplicationUtils.sha256Hex(oldPwd).equalsIgnoreCase(user.getPassword())){
    			map.put("flag", 1);
    		}
    	}
    	return map;
    }
    
    /**
     * 修改密码提交保存
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String updatePwd(HttpServletRequest request,HttpSession session){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	String newPwd=request.getParameter("newPwd");
    	User sessionUser=(User) request.getSession().getAttribute("userInfo");
    	User user=new User();
    	user.setId(sessionUser.getId());
    	user.setPassword(ApplicationUtils.sha256Hex(newPwd));
    	int n=userService.update(user);
    	session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if(n>0){
        	map.put("flag", 1);
        }
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String resetPassword(Integer userId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(userId!=null){
    		User user=new User();
    		user.setId(userId);
        	user.setPassword(ApplicationUtils.sha256Hex(com.midland.web.security.Resource.DEFAULT_PASSWORD));
        	if(userService.update(user)>0){
        		map.put("flag", 1);
        	}
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 找回密码发送邮件
     * @return
     */
    @RequestMapping(value = "/sendMail", method = {RequestMethod.GET,RequestMethod.POST})
    public String sendMail(HttpServletRequest request,String email,String un){
    	String secretKey = UUID.randomUUID().toString(); // 密钥
    	Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);//过期时间： 30分钟后过期
    	long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
    	
    	User user=userService.selectByUsername(un);
    	if(user!=null){
//    		user.set
    		//更新数据库
        	//userService.update(user);
    		
    	}
    	
    	
    	
    	String key =un + "$" + date + "$" + secretKey;
    	String digitalSignature = MD5Util.MD5Encode(key, null);// 数字签名
    	String basePath=request.getRequestURL().toString();
    	//http://localhost:8080/webmvc/rest/user/sendMail
    	basePath=basePath.substring(0,basePath.lastIndexOf("/")+1);
    	String resetPassHref = basePath  + "checkLink?sid="+ digitalSignature +"&userName="+un;
    	String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                + resetPassHref + " target='_BLANK'>" + resetPassHref
                + "</a>  或者    <a href=" + resetPassHref
                + " target='_BLANK'>点击我重新设置密码</a>"
                + "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" + key
                + "\t" + digitalSignature;

	    
        SmsUtil.send("13602825350", "xcv12345678");
    	
    	return "login";
    }
    
    /**
     * 验证密码找回链接
     * @return
     */
    @RequestMapping(value = "/checkLink", method = {RequestMethod.GET,RequestMethod.POST})
    public String checkResetLink(Model model,HttpServletRequest request){
    	String sid=request.getParameter("sid");
    	String userName=request.getParameter("userName");
    	
    	if (sid.equals("")  || userName.equals("")) {
    		model.addAttribute("mesg", "链接不完整,请重新生成");
            return "error";
        }
    	
    	User user=userService.selectByUsername(userName);
    	if(user!=null){
    		Timestamp outDate= new Timestamp(1111);//数据库用户过期时间
        	
        	if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
        		model.addAttribute("mesg", "链接已经过期,请重新申请找回密码.");
                return "error";
            }
        	
        	String key = user.getUsername()+"$"+outDate.getTime()/1000*1000+"$"+"user.getValidataCode()";//数字签名
        	String digitalSignature = MD5Util.MD5Encode(key, null);// 数字签名
        	
        	if(!digitalSignature.equals(sid)) {
        		model.addAttribute("mesg", "链接不正确,是否已经过期了?重新申请吧.");
                return "error";
            }else {
              //链接验证通过 转到修改密码页面
            	model.addAttribute("user", user);
              return "redirect:/";
          }
    	}else{
    		 return "redirect:/";
    	}
    }

    /**
     * 发送短信
     * @param username
     * @return
     */
    @RequestMapping(value = "/sendSms",produces = "application/json; charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String sendSms(String username){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	
    	String vcode = SmsUtil.createRandomVcode();//验证码
    	String mobile="";
    	String key="wks:vcode:"+username;
    	String content="";
    	User user=userService.selectByUsername(username);
    	if(user!=null){
    		mobile=user.getPhone();
    		content="【沃可视】 您正在重置密码,验证码:"+vcode+",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人。";
    		if(mobile!=null && mobile.length()>0){
        		if(SmsUtil.send(mobile, content)){
            		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
                	vo.set(key, vcode);
                	redisTemplate.expire(key, 15,TimeUnit.MINUTES);//15分钟过期
                	map.put("flag", 1);
                	map.put("msg", "发送成功!");
            	}else{
            		map.put("msg", "短信发送失败，请稍后再试!");
            	}
        	}else{
        		map.put("msg", "该用户名未绑定有效的手机号码!");
        	}
    	}else{
    		map.put("msg", "无效的用户名!");
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 验证码校验
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/checkVcode", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  String checkVcode(String username,String vcode){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	String key="wks:vcode:"+username;
    	ValueOperations<String, Object> vo = redisTemplate.opsForValue();
    	String redisVcode=vo.get(key).toString();
    	if(redisVcode.equals(vcode)){
    		map.put("flag", 1);
    	}
    	return JSONObject.toJSONString(map);
    }
    
    /**
     * 中间首页图
     * @return
     */
    @RequestMapping(value = "/contentIndex", method = {RequestMethod.GET,RequestMethod.POST})
    public String contentIndex(){
    	
    	return "contentIndex";
    }
    
    /**
     * 跳转到关于页面
     * @return
     */
    @RequestMapping(value = "/about", method = {RequestMethod.GET,RequestMethod.POST})
    public String about(){
    	
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
    public void userInfoExportExcel(User user, HttpServletResponse response){
	    List<User> dataList = userService.selectUserList(user);
	    PoiExcelExport pee = new PoiExcelExport(response,"用户","sheet1");
	    //调用
	    String titleColumn[] = {"id","username","userCnName","userType","state"};
	    String titleName[] = {"用户id","用户名","昵称","用户类型","状态"};
	    int titleSize[] = {13,13,13,13,13};
	    //其他设置 set方法可全不调用
	    pee.wirteExcel(titleColumn, titleName, titleSize, dataList);
    }
    
    
    
    
}
