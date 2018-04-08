package com.midland.web.base;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@SuppressWarnings("all")
public abstract class BaseFilter{

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
		response.setContentType("text/html;charset=utf-8");		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(info);
	}
}
