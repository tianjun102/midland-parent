package com.midland.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midland.core.util.SmsUtil;
import com.midland.core.util.UploadImgUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.core.util.ApplicationUtils;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.commons.exception.ServiceException;
import com.midland.web.model.WebUser;
import com.midland.web.service.WebUserService;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;

/**
 * @Title: UserController.java
 * @Package com.bluemobi.midland.web.controller
 * @Description: 用户表示层类
 * @author yek
 * @date 2017年3月22日 上午8:53:59
 */
@RestController
@RequestMapping(value = "/user")
public class WebUserController extends WebCommonsController {

	private static Logger logger = Logger.getLogger(WebUserController.class);

	@Resource
	private WebUserService webUserService;

	@Resource
	private ApiHelper apiHelper;
@Resource
	private PublicService publicServiceImpl;


    @Resource
	private RedisTemplate<String, Object> redisTemplate;
    

	/**
	 * 用户登录
	 * 
	 * @param remember
	 *            是否记住密码
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login(@RequestBody WebUser user, boolean remember, HttpServletRequest request,
                        HttpServletResponse response) {
		Result<WebUser> result = new Result<>();
		// 登录日志状态：0,正常;1,错误
		Short isError = 0;
		// 获取当前登录用户IP
		String loginIP = request.getRemoteAddr();

		try {
			// 判断用户名和密码是否为空值
			if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {

				WebUser userInfo = webUserService.getUserByUsername(user.getUsername());

				if (null != userInfo) {

					// 获取用户输入密码进行加密处理
					String encryptedPwd = ApplicationUtils.sha256Hex(user.getPassword());

					if (userInfo.getPassword().equals(encryptedPwd)) {

						// 用户登录验证成功后保存用户信息到SESSION
						
						/*if(userInfo.getId()!=null){
							userInfo.setMsgNum(noticeService.countNoticeNum(userInfo.getId()));
						}*/
						
						request.getSession().setAttribute(ConstantUtils.USER_SESSION, userInfo);
						if (userInfo.getUserType() == 2) {
							WebUser catUser = webUserService.findParentUserByChild(userInfo.getUsername());
							if(catUser!=null){
							userInfo.setParentId(catUser.getId());
							userInfo.setCustName(catUser.getCustName());
							}
						}
						String sessionId = request.getSession().getId();

						// "1"表示用户勾选记住密码
						if (remember) {

							// 创建Cookie
							Cookie usernameCookie = new Cookie(ConstantUtils.USER_INFO_COOKIE_USERNAME,
									user.getUsername());

							usernameCookie.setPath(request.getContextPath());
							// cookie保存用户信息时限
							usernameCookie.setMaxAge(99999999);
							response.addCookie(usernameCookie);

							// 创建Cookie
							Cookie encryptedPwdCookie = new Cookie(ConstantUtils.USER_INFO_COOKIE_PASSWORD,
									encryptedPwd);

							encryptedPwdCookie.setPath(request.getContextPath());
							// cookie保存用户信息时限
							encryptedPwdCookie.setMaxAge(99999999);
							response.addCookie(encryptedPwdCookie);
						}
						result.setToken(sessionId);
						result.setCode(ResultStatusUtils.STATUS_CODE_200);
						result.setMsg(Result.resultMsg.SUCCESS.toString());
						result.setModel(userInfo);
					} else {
						isError = 1;
						result.setCode(ResultStatusUtils.STATUS_CODE_203);
						result.setMsg("输入密码不正确。");
						logger.warn("输入密码不正确。");
					}
				} else {
					isError = 1;
					result.setCode(ResultStatusUtils.STATUS_CODE_203);
					result.setMsg("用户不存在。");
					logger.warn("用户不存在。");
				}

			}
		} catch (ServiceException e) {
			isError = 1;
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("用户名密码错误！");
			logger.error("用户登录时出现异常。", e);
		}

		// 添加登录日志
		/*sysLogService.addSysLog(
				new SysLog(Short.valueOf("0"), user.getUsername() + "用户登录", loginIP, user.getUsername(), isError));*/
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Result<WebUser> result = new Result<>();
		HttpSession session = request.getSession();
		/** 删除session用户信息 */
		session.removeAttribute(ConstantUtils.USER_SESSION);
		session.invalidate();

		/** 删除cookie用户信息 */
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (ConstantUtils.USER_INFO_COOKIE_USERNAME.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					break;
				}
				if (ConstantUtils.USER_INFO_COOKIE_PASSWORD.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					break;
				}
			}
		}
		result.setCode(ResultStatusUtils.STATUS_CODE_200);
		result.setMsg(Result.resultMsg.SUCCESS.toString());
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = { RequestMethod.POST },produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String editPwd(@RequestBody Map<String, String> params, HttpServletRequest request) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("oldPassWord"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("原始密码为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("passWord"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("新密码为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		WebUser user = (WebUser) request.getSession().getAttribute(ConstantUtils.USER_SESSION);
		if (null == user) {
			result.setCode(ResultStatusUtils.STATUS_CODE_303);
			result.setMsg("用户已退出，请重新登录！");
			return FastJsonUtils.toJSONStr(result);
		}
		if(!ApplicationUtils.sha256Hex(params.get("oldPassWord")).equals(user.getPassword())){
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("原始密码不正确!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		
		try {
				int i = webUserService.editPwdById(ApplicationUtils.sha256Hex(params.get("passWord")), user.getId());
				if (i>0) {
					result.setCode(ResultStatusUtils.STATUS_CODE_200);
					result.setMsg("修改密码成功!");
				}else{
					result.setCode(ResultStatusUtils.STATUS_CODE_200);
					result.setMsg("请求成功,密码尚未被修改!");
				}
		} catch (ServiceException e) {
			logger.error("修改密码时出现异常。", e);
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改密码失败!");
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 跳到个人信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String toEdit(HttpServletRequest request, @RequestBody Map<String,String> params) {
		Result<WebUser> result = new Result<>();
		WebUser user = webUserService.getUserByUserId(Integer.valueOf(params.get("userId")));

		result.setCode(ResultStatusUtils.STATUS_CODE_200);
		result.setMsg(Result.resultMsg.SUCCESS.toString());
		result.setModel(user);

		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 修改个人信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String editInformtion(@RequestBody Map<String, String> params, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Result<WebUser> result = new Result<>();
		try {
			Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
			if (null != obj) {

				WebUser user = (WebUser) obj;

				user.setPhone(params.get("phone"));
				user.setEmail(params.get("email"));

				int i = webUserService.editUserById(user);
				result.setNumber(i);
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg(Result.resultMsg.SUCCESS.toString());
			}
		} catch (ServiceException e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改个人信息时出现异常。");
			logger.error("修改个人信息时出现异常。", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/*上传图像*/
	@RequestMapping(value = "/changeHeadImg", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String changeHeadImg(@RequestBody Map<String, String> params,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Result<WebUser> result = new Result<>();
		try {
			if (params == null ||params.isEmpty()) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("请求参数为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			if(StringUtils.isEmpty(params.get("imgContent"))){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("图片内容为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			
			Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
			if (null != obj) {
				WebUser user = (WebUser) obj;
				Map<String, String> map = Maps.newHashMap();
				map.put("path", "/home/upload/");
				map.put("userName", user.getUsername());
				map.put("oldImg",user.getHeadImg());
				map.put("imgContent",params.get("imgContent"));
				String headImg  = UploadImgUtil.GenerateImage(map);

				if(StringUtils.isNotEmpty(headImg)){
					user.setHeadImg(headImg);
				}
				int i = webUserService.editUserById(user);
				result.setNumber(i);
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg(Result.resultMsg.SUCCESS.toString());
			}
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改头像出错,文件不正确。");
			logger.error("修改头像出错,文件不正确", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 用户认证
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authUser", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String authUser(@RequestBody Map<String, String> params,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Result<WebUser> result = new Result<>();
		try {
			if (params == null ||params.isEmpty()) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("请求参数为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			if(StringUtils.isEmpty(params.get("idcartImg"))){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("身份正面为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			if(StringUtils.isEmpty(params.get("idcartImg１"))){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("身份反面为空!");
				return FastJsonUtils.toJSONStr(result);
			}

			Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
			if (null != obj) {
				WebUser user = (WebUser) obj;
				user.setIdentification(params.get("identification"));
				user.setActualName(params.get("actualName"));
				Map<String, String> map = Maps.newHashMap();
				map.put("path", "/home/upload/");
				map.put("userName", user.getUsername());
				map.put("oldImg",user.getIdcartImg());
				map.put("imgContent",params.get("idcartImg"));
				String headImg  = UploadImgUtil.GenerateImage(map);

				if(StringUtils.isNotEmpty(headImg)){
					user.setIdcartImg(headImg);
				}
				map.put("imgContent",params.get("idcartImg1"));
				String headImg1  = UploadImgUtil.GenerateImage(map);
				if(StringUtils.isNotEmpty(headImg)){
					user.setIdcartImg(headImg1);
				}

				int i = webUserService.editUserById(user);
				result.setNumber(i);
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg(Result.resultMsg.SUCCESS.toString());
			}
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改头像出错,文件不正确。");
			logger.error("修改头像出错,文件不正确", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 检查用户名
	 * 
	 *            用户名
	 * @return
	 */
	@RequestMapping(value = "/checkPhone", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String checkPhone(@RequestBody Map<String, String> params) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("phone"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("手机号码为空!");
			return FastJsonUtils.toJSONStr(result);
		}

		try {
			int i = webUserService.authentication(params.get("phone"));
			result.setNumber(i);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("校验手机号成功!");
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("校验手机号失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 注册发送验证码
	 * @param map
	 */
	@RequestMapping("send/code")
	public void sign_up_SendSMS(@RequestBody Map map) {
		Result result = new Result();
		try {
			String phone = (String) map.get("phone");
			if (StringUtils.isEmpty(phone)) {
				throw new Exception("手机号码为空");
			}
			String vcode = SmsUtil.createRandomVcode();//验证码
			String key = Contant.SIGN_UP_VCODE_KEY + phone;
			publicServiceImpl.setV(key, vcode, 1, TimeUnit.MINUTES);
			List list = new ArrayList();
			list.add(vcode);
			list.add("1");
			apiHelper.smsSender(phone, 54711, list);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch (Exception e) {
			logger.error("发送验证码失败", e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("发送验证码失败 ");
		}

	}

	/**
	 * 校验验证码
	 * @param map
	 * @return
	 */
	@RequestMapping("checkVcode")
	public Object checkVcode_(@RequestBody Map map) {
		Result result = new Result();
		try {
			String phone = (String) map.get("phone");
			String vcode = (String) map.get("vcode");
			String key = Contant.SIGN_UP_VCODE_KEY + phone;
			String redisVcode = (String) publicServiceImpl.getV(key);
			if (redisVcode.equals(vcode)) {
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg("success");
			}else {
				result.setCode(ResultStatusUtils.STATUS_CODE_203);
				result.setMsg("error");
			}
		} catch (Exception e) {
			logger.error("checkVcode_", e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("error");
		}
		return result;
	}

	@RequestMapping("/sign_up")
	public Object sign_up(@RequestBody Map<String, String> param) {
		Result result = new Result();
		Result result1 = (Result)checkVcode_(param);
		if (ResultStatusUtils.STATUS_CODE_200 == result1.getCode()) {
			try {
				String password = param.get("password");
				String confirmPassword = param.get("confirmPassword");
				if (password != null && password.equals(confirmPassword)) {
					webUserService.addUser(param);
					result.setCode(ResultStatusUtils.STATUS_CODE_200);
					result.setMsg("sign up success");
				} else {
					result.setCode(ResultStatusUtils.STATUS_CODE_203);
					result.setMsg("Password and Confirm Password inconsistent!");
				}
			} catch (Exception e) {
				logger.error("sign_up:", e);
				result.setCode(ResultStatusUtils.STATUS_CODE_203);
				result.setMsg("sign up success error");
			}
		}
		result.setCode(ResultStatusUtils.STATUS_CODE_203);
		result.setMsg("vcode is incorrect");
		return result;
	}

	
}
