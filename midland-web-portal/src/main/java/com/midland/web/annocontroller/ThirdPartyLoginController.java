package com.midland.web.annocontroller;
import com.midland.core.entity.ThirdPartyUser;
import com.midland.core.util.ThirdPartyLoginHelper;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.WebUser;
import com.midland.web.service.WebUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping(value = "/thirdParty")
public class ThirdPartyLoginController {
    @Resource
    private WebUserService userService;

    @RequestMapping(value = "/contentIndex", method = {RequestMethod.GET,RequestMethod.POST})
    public  String contentIndex(HttpServletRequest request, HttpServletResponse response){
        return "contentIndex";

    }

    @RequestMapping(value = "/qqlogin", method = {RequestMethod.GET,RequestMethod.POST})
    public  void checkVcode(HttpServletRequest request, HttpServletResponse response){
        /*response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


    @RequestMapping(value = "/callback/qq", method = {RequestMethod.GET,RequestMethod.POST})
    public String qqCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) {
        Result<WebUser> result = new Result<>();
        String sessionId = request.getSession().getId();
        String host = request.getHeader("host");
        try {
            String code = request.getParameter("code");
            if (StringUtils.isNotBlank(code)) {// 如果不为空
                // 获取token和openid
                Map<String, String> map = ThirdPartyLoginHelper.getQQTokenAndOpenid(code, host);
                String openId = map.get("openId");
                if (StringUtils.isNotBlank(openId)) {// 如果openID存在
                    // 获取第三方用户信息存放到session中
                    ThirdPartyUser thirdUser = ThirdPartyLoginHelper.getQQUserinfo(map.get("access_token"), openId);
                    thirdUser.setProvider("QQ");
                    //************开始*************//
                    WebUser userInfo = new WebUser();
                    userInfo.setQqOpenId(openId);
                    userInfo = userService.findtUserByEntity(userInfo);
                    if(userInfo==null){
                        userInfo = new WebUser();
                        userInfo.setQqOpenId(openId);
                        userInfo.setUserCnName(thirdUser.getUserName());
                        userInfo.setHeadImg(thirdUser.getAvatarUrl());
                        userInfo.setUserType(2);
                        userInfo.setState(1);
                        Integer id =  userService.addWebUser(userInfo);
                        userInfo.setId(id);
                        request.getSession().setAttribute(ConstantUtils.USER_SESSION, userInfo);
                    }
                    result.setToken(sessionId);
                    result.setCode(ResultStatusUtils.STATUS_CODE_200);
                    result.setMsg(Result.resultMsg.SUCCESS.toString());
                    result.setModel(userInfo);
                    redirectAttributes.addFlashAttribute("result",result);
                    return "redirect:http://175.102.18.14:8083/web?token="+sessionId+"&code="+userInfo.getId();
                    //************结束*************//
                } else {// 如果未获取到OpenID
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg(Result.resultMsg.SUCCESS.toString());
                    redirectAttributes.addFlashAttribute("result",result);
                }
            } else {// 如果没有返回令牌，则直接返回到登录页面
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg(Result.resultMsg.SUCCESS.toString());
                redirectAttributes.addFlashAttribute("result",result);
            }
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg(Result.resultMsg.SUCCESS.toString());
            redirectAttributes.addFlashAttribute("result",result);
            e.printStackTrace();
        }

        return "redirect:http://175.102.18.14:8083/web?token=error";




    }


}
