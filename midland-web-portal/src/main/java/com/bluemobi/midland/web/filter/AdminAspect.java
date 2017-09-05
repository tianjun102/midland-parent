package com.bluemobi.midland.web.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.bluemobi.midland.web.commons.FastJsonUtils;
import com.bluemobi.midland.web.commons.Result;
import com.bluemobi.midland.web.commons.core.util.ConstantUtils;
import com.bluemobi.midland.web.commons.core.util.ResultStatusUtils;
import com.bluemobi.midland.web.model.User;

@Component
@Aspect
public class AdminAspect {

	HttpServletRequest request;
	HttpServletResponse response;

	@Before("execution(* com.bluemobi.midland.web.controller.*.*(..))")
	public void adminPrivilegeCheck() throws Exception {
		HttpSession session = SysContext.getSession();
		request = SysContext.getRequest();
		response = SysContext.getResponse();

		Result<?> result = new Result<>();
		User user = null;
		String url = request.getRequestURI();
		if (url.indexOf("/login") < 0) {
			user = (User) session.getAttribute(ConstantUtils.USER_SESSION);
			if (null == user) {
				PrintWriter out = null;
				result.setCode(ResultStatusUtils.STATUS_CODE_303);
				result.setMsg("未登录或登录已过期，请重新登录。");
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.print(FastJsonUtils.toJSONStr(result));
				out.flush();
				out.close();
				throw new Exception("访问权限有限.");
			}
		}
	}
}