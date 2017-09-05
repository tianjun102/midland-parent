<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("X-Frame-Options", "SAMEORIGIN");
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<%--<link rel="stylesheet" href="${ctx}/assets/css/layer.css">--%>

<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/plugins/jquery-validation/lib/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script src="${ctx}/assets/scripts/common.js"></script>
<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/assets/UEditor/ueditor.config.js" type="text/javascript"></script>
<script src="${ctx}/assets/UEditor/ueditor.all.js" type="text/javascript"></script>
<script src="${ctx}/assets/UEditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/inputControl.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/assets/scripts/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${ctx }/assets/scripts/echarts.min.js"></script>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/assets/scripts/uploadify/uploadify.css">
