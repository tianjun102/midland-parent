<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("X-Frame-Options", "SAMEORIGIN");
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
%>
<body>
<c:if test="${not empty pageNo}">
    <input type="hidden" name="pageNo" value="${pageNo}"/>
</c:if>
<c:if test="${not empty pageSize}">
    <input type="hidden" name="pageSize" value="${pageSize}"/>
</c:if>
</body>

