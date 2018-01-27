package com.midland.web.filter;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.WebUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;

@Component
@Aspect
public class AdminAspect {

	HttpServletRequest request;
	HttpServletResponse response;


	@Before("execution(* com.midland.web.controller.*.*(..))")
	public void adminPrivilegeCheck() throws Exception {
		HttpSession session = SysContext.getSession();
		request = SysContext.getRequest();
		response = SysContext.getResponse();
		Result<?> result = new Result<>();
		String url = request.getRequestURI();
		if (url.indexOf("/login") < 0) {
			WebUser user = (WebUser) session.getAttribute(ConstantUtils.USER_SESSION);
			if (null == user) {
				result.setCode(ResultStatusUtils.STATUS_CODE_303);
				result.setMsg("未登录或登录已过期，请重新登录。");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(FastJsonUtils.toJSONStr(result));
				out.flush();
				out.close();
				throw new Exception("访问权限有限.");
			}
		}
	}
}