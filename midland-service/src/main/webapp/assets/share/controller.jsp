<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.midland.ueditor.ActionEnter" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	String rootPath = application.getRealPath( "/" );
	out.write( new ActionEnter( request, rootPath ).exec() );
	
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>