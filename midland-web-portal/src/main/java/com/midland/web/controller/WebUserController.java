package com.midland.web.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.google.common.collect.Maps;
import com.midland.config.SftpProperties;
import com.midland.core.util.SmsUtil;
import com.midland.core.util.UploadImgUtil;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ApplicationUtils;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.commons.exception.ServiceException;
import com.midland.web.model.WebUser;
import com.midland.web.model.user.User;
import com.midland.web.service.WebUserService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yek
 * @Title: UserController.java
 * @Package com.bluemobi.midland.web.controller
 * @Description: 用户表示层类
 * @date 2017年3月22日 上午8:53:59
 */
@RestController
@RequestMapping(value = "/user")
public class WebUserController extends WebCommonsController {

    private static Logger logger = LoggerFactory.getLogger(WebUserController.class);

    @Resource
    private WebUserService userService;


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SmsSingleSender sender;


    /**
     * 用户登录
     *
     * @param remember 是否记住密码
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
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

                WebUser userInfo = userService.getUserByUsername(user.getUsername());

                if (null != userInfo) {

                    // 获取用户输入密码进行加密处理
                    String encryptedPwd = ApplicationUtils.sha256Hex(user.getPassword());

                    if (userInfo.getPassword().equals(encryptedPwd)) {

                        // 用户登录验证成功后保存用户信息到SESSION

						/*if(userInfo.getId()!=null){
                            userInfo.setMsgNum(noticeService.countNoticeNum(userInfo.getId()));
						}*/

                        request.getSession().setAttribute(ConstantUtils.USER_SESSION, userInfo);
                        request.getSession().setMaxInactiveInterval(1800);
						/*if (userInfo.getUserType() == 2) {
							WebUser catUser = userService.findParentUserByChild(userInfo.getUsername());
							if(catUser!=null){
							userInfo.setParentId(catUser.getId());
							userInfo.setCustName(catUser.getCustName());
							}
						}*/
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
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(value = "/editPwd", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String editPwd(@RequestBody Map<String, String> params, HttpServletRequest request) {
        Result<WebUser> result = new Result<>();
        if (params == null || params.isEmpty()) {
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
        if (!ApplicationUtils.sha256Hex(params.get("oldPassWord")).equals(user.getPassword())) {
            result.setCode(ResultStatusUtils.STATUS_CODE_202);
            result.setMsg("原始密码不正确!");
            return FastJsonUtils.toJSONStr(result);
        }


        try {
            int i = userService.editPwdById(ApplicationUtils.sha256Hex(params.get("passWord")), user.getId());
            if (i > 0) {
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("修改密码成功!");
            } else {
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
    @RequestMapping(value = "/toEdit", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String toEdit(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Result<WebUser> result = new Result<>();
        WebUser user = userService.getUserByUserId(params.get("userId"));

        result.setCode(ResultStatusUtils.STATUS_CODE_200);
        result.setMsg(Result.resultMsg.SUCCESS.toString());
        result.setModel(user);

        return FastJsonUtils.toJSONStr(result);
    }





    /**
     * 修改个人邮箱
     *
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/edit_email", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object edit_email(@RequestBody Map map, HttpServletRequest request) {
        Result<WebUser> result = new Result<>();
        String id = String.valueOf(map.get("id"));
        String email = String.valueOf(map.get("email"));
        if (id == null || StringUtils.isEmpty(email)) {
            result.setCode(ResultStatusUtils.STATUS_CODE_202);
            result.setMsg("id或email不能为空");
            return result;
        }
        WebUser user = new WebUser();
        user.setId(id);
        user.setEmail(email);

        return edit_user(result, user);

    }


    /**
     * 修改个人昵称
     *
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/edit_userCnName", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object edit_userCnName(@RequestBody Map map, HttpServletRequest request) {
        Result<WebUser> result = new Result<>();
        String id = String.valueOf(map.get("id"));
        String userCnName = String.valueOf(map.get("userCnName"));
        if (id == null || StringUtils.isEmpty(userCnName)) {
            result.setCode(ResultStatusUtils.STATUS_CODE_202);
            result.setMsg("id或userCnName不能为空");
            return result;
        }
        WebUser user = new WebUser();
        user.setId(id);
        user.setUserCnName(userCnName);

        return edit_user(result, user);

    }

    /**
     * 修改个人手机
     *
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/edit_phone", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object edit_phone(@RequestBody Map map, HttpServletRequest request) {
        Result<WebUser> result = new Result<>();
        String id = String.valueOf(map.get("id"));
        String phone = String.valueOf(map.get("phone"));
        if (id == null || StringUtils.isEmpty(phone)) {
            result.setCode(ResultStatusUtils.STATUS_CODE_202);
            result.setMsg("id或phone不能为空");
            return result;
        }
        WebUser user = new WebUser();
        user.setId(id);
        user.setPhone(phone);

        return edit_user(result, user);

    }

    private Object edit_user(Result<WebUser> result, WebUser user) {
        try {
            int i = userService.editUserById(user);
            result.setNumber(i);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg(Result.resultMsg.SUCCESS.toString());
            return result;
        } catch (ServiceException e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_500);
            result.setMsg("修改个人信息时出现异常。");
            logger.error("修改个人信息时出现异常。", e);
            return result;
        }
    }


    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editInformtion(@RequestBody WebUser user, HttpServletRequest request) {
        Result<WebUser> result = new Result<>();
        try {
            if (null != user) {
                user.setPassword(null);
                int i = userService.editUserById(user);
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
    @RequestMapping(value = "/changeHeadImg", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String changeHeadImg(@RequestBody Map<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Result<WebUser> result = new Result<>();
        try {
            if (params == null || params.isEmpty()) {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("请求参数为空!");
                return FastJsonUtils.toJSONStr(result);
            }
            if (StringUtils.isEmpty(params.get("imgContent"))) {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("图片内容为空!");
                return FastJsonUtils.toJSONStr(result);
            }

            Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
            if (null != obj) {
                WebUser user = (WebUser) obj;
                Map<String, String> map = Maps.newHashMap();
                StringBuilder sb=new StringBuilder();
                sb.append(SftpProperties.getInstance().getImgPath()).append("headImg/").append(MidlandHelper.formatFileNameMonth(MidlandHelper.getCurrentTime())).append("/");
                map.put("path", sb.toString());
                map.put("fileName", user.getUsername());
                map.put("oldImg", user.getHeadImg());
                map.put("imgContent", params.get("imgContent"));
                String headImg = UploadImgUtil.GenerateImage(map);

                if (StringUtils.isNotEmpty(headImg)) {
                    user.setHeadImg(headImg);
                }
                int i = userService.editUserById(user);
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
     *
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/authUser", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String authUser(@RequestBody Map<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Result<WebUser> result = new Result<>();
        try {
            if (params == null || params.isEmpty()) {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("请求参数为空!");
                return FastJsonUtils.toJSONStr(result);
            }
            if (StringUtils.isEmpty(params.get("idcartImg"))) {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("身份正面为空!");
                return FastJsonUtils.toJSONStr(result);
            }
            if (StringUtils.isEmpty(params.get("idcartImg1"))) {
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
                map.put("fileName", "idcartImg");
                map.put("oldImg", user.getIdcartImg());
                map.put("imgContent", params.get("idcartImg"));
                String headImg = UploadImgUtil.GenerateImage(map);

                if (StringUtils.isNotEmpty(headImg)) {
                    user.setIdcartImg(headImg);
                }
                map.put("imgContent", params.get("idcartImg1"));
                map.put("fileName", "idcartImg1");
                String headImg1 = UploadImgUtil.GenerateImage(map);
                if (StringUtils.isNotEmpty(headImg)) {
                    user.setIdcartImg(headImg1);
                }

                int i = userService.editUserById(user);
                result.setNumber(i);
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg(Result.resultMsg.SUCCESS.toString());
            }
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_500);
            result.setMsg("用户认证出错,文件不正确。");
            logger.error("用户认证出错,文件不正确", e);
        }
        return FastJsonUtils.toJSONStr(result);
    }

    /**
     * 检查用户名
     * <p>
     * 用户名
     *
     * @return
     */
    @RequestMapping(value = "/checkPhone", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String checkPhone(@RequestBody Map<String, String> params) {
        Result<WebUser> result = new Result<>();
        if (params == null || params.isEmpty()) {
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
            int i = userService.authentication(params.get("phone"));
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
     * 发送短信
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/vcode/sendSms")
    public String vcodeSendSms(String phone) {
        WebUser user = new WebUser();
        user.setPhone(phone);
        Map<Object, Object> map = new HashMap<Object, Object>();
        Result<WebUser> result_ = new Result<>();
        map.put("flag", 0);
        if (StringUtils.isEmpty(phone)) {
            map.put("msg", "手机号不能为空！");
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_203);
            result_.setMsg("发送失败！");
            return FastJsonUtils.toJSONStr(result_);
        }
        String vcode = SmsUtil.createRandomVCode();//验证码
        String mobile = "";
        String key = "midland:vcode:" + phone;
        user = userService.findtUserByEntity(user);
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
                        result_.setMap(map);
                        result_.setCode(ResultStatusUtils.STATUS_CODE_200);
                        result_.setMsg(Result.resultMsg.SUCCESS.toString());
                        return FastJsonUtils.toJSONStr(result_);

                    } else {
                        map.put("id", user.getId());
                        map.put("msg", "短信发送失败，请稍后再试!");
                        result_.setMap(map);
                        result_.setCode(ResultStatusUtils.STATUS_CODE_200);
                        result_.setMsg("发送失败！");
                        return FastJsonUtils.toJSONStr(result_);
                    }
                } catch (Exception e) {
                    map.put("id", user.getId());
                    map.put("msg", "短信发送失败，请稍后再试!");
                }

            } else {
                map.put("msg", "该用户名未绑定有效的手机号码!");
                result_.setMap(map);
                result_.setCode(ResultStatusUtils.STATUS_CODE_203);
                result_.setMsg("发送失败！");
                return FastJsonUtils.toJSONStr(result_);
            }
        } else {
            map.put("msg", "无效的手机号!");
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_203);
            result_.setMsg("发送失败！");


        }
        return FastJsonUtils.toJSONStr(result_);
    }


    /**
     * 验证码校验
     *
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/vcode/checkVcode", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkVcode_(String phone, String vcode) {
        Result<WebUser> result_ = new Result<>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("flag", 0);
        String key = "midland:vcode:" + phone;
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        String redisVcode = vo.get(key).toString();
        if (redisVcode.equals(vcode)) {
            map.put("flag", 1);
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_200);
            result_.setMsg(Result.resultMsg.SUCCESS.toString());
            return FastJsonUtils.toJSONStr(result_);
        } else {
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_203);
            result_.setMsg(Result.resultMsg.SUCCESS.toString());

        }
        return FastJsonUtils.toJSONStr(result_);
    }


    @RequestMapping("/getUserInfo")
    public Object getUserInfo(HttpServletRequest request){
        logger.info("getUserInfo x-auth-token:{},userId:{}",request.getHeader("x-auth-token"),request.getParameter("userId"));
        Result result = new Result();
        try {
            String userId = request.getParameter("userId");
            if (StringUtils.isEmpty(userId)){
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("userId不能为空");
                return result;
            }
            WebUser user = userService.getUserByUserId(userId);
            if (user!=null){
                user.setPassword(null);
            }

            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(user);
            return result;
        } catch (Exception e) {
            logger.error("getUserInfo ",e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            return result;
        }
    }

}
