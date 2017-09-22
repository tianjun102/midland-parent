<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp"%>
<%--<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>--%>
<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/assets/scripts/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
<style type="text/css">
.ztree li span.button.pIcon01_ico_open{margin-right:2px; background: url(${ctx}/assets/css/ztree/css/zTreeStyle/img/diy/1_open.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon01_ico_close{margin-right:2px; background: url(${ctx}/assets/css/ztree/css/zTreeStyle/img/diy/1_close.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon02_ico_open, .ztree li span.button.pIcon02_ico_close{margin-right:2px; background: url(${ctx}/assets/css/ztree/css/zTreeStyle/img/diy/2.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon03_ico_open, .ztree li span.button.pIcon03_ico_close{margin-right:2px; background: url(${ctx}/assets/css/ztree/css/zTreeStyle/img/diy/file.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon03_ico_docu{margin-right:2px; background: url(${ctx}/assets/css/ztree/css/zTreeStyle/img/diy/file.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>