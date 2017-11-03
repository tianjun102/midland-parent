package com.bluemobi.midland.web.annocontroller;
import com.midland.core.entity.ThirdPartyUser;
import com.midland.core.util.ThirdPartyLoginHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/thirdParty")
public class ThirdPartyLoginController {

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
    public String qqCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

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
                    //此处调用登录方法登录到我们系统中
                    //************结束*************//
                    // 跳转到登录成功界面
                    modelMap.put("retUrl", "");
                } else {// 如果未获取到OpenID
                    modelMap.put("retUrl", "-1");
                }
            } else {// 如果没有返回令牌，则直接返回到登录页面
                modelMap.put("retUrl", "-1");
            }
        } catch (Exception e) {
            modelMap.put("retUrl", "-1");
            e.printStackTrace();
        }

        return "/sns/redirect";




    }


}
