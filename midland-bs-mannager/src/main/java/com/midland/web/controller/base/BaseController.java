package com.midland.web.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
@Controller
public abstract class BaseController {
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		InitStringToNull stringEditor = new InitStringToNull();
		binder.registerCustomEditor(String.class, stringEditor);
	}
	
	
	@ExceptionHandler({Exception.class})
	public void handlerException(Exception e, HttpServletResponse response) throws IOException {
		e.printStackTrace();
		responseInfo(response, "系统繁忙，请重试！...");
	}
	
	private void responseInfo(HttpServletResponse response, String info) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(info);
	}
}
