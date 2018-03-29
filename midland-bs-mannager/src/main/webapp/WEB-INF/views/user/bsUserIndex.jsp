<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .content ul.userinfo li > span {
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>
</head>
<body>


<!--用户列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>系统管理>>后台用户管理</span>
            <a class="setup" target="contentF" onclick="toAddPage()">创建用户</a>
        </p>
        <form action="${ctx }/rest/user/bsUserList" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <input type="hidden" name="userType" value="2"/>
                <li><span>用户名：</span><input type="text" name="username" id="username" placeholder="请输入用户名"/></li>
                <li><span>手机号码：</span><input type="text" name="phone" id="phone" placeholder="请输入手机号码"/></li>
                <li><span>实名状态：</span>
                    <select name="auditStatus" id="auditStatus" class="dropdown">
                        <option value="">请选择</option>
                        <option value="0">未审核</option>
                        <option value="1">已审核</option>
                        <option value="2">拒绝</option>
                    </select>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value="">请选择</option>
                        <c:forEach items="${sources}" var="s">
                            <option value="${s.id}">
                                    ${s.name}
                            </option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>审核人：</span><input type="text" name="auditName" id="auditName" placeholder="请输入审核人"/></li>
                <li><span>注册时间：</span><input class="Wdate half" id="time1"
                                             onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                                             name="startTime"/> <em class="gang">-</em><input
                        class="Wdate half"
                        onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                        id="time2" name="endTime"/></li>

                <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
            </ul>
        </form>
        <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
               onclick="exportUsers()" class="public_btn bg1" type="submit" value="导出"/>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">

    window.onload = function () {
        $('#searchForm').submit();
    }


    function exportUsers() {
        var data = $("#searchForm").serialize();

        window.location.href = "${ctx}/rest/user/export?" + data;
    }


    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '450px'],
            shadeClose: false, //点击遮罩关闭
            title: ['创建用户'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/user/toAddPage', 'no']
        });
    }
</script>

</body>
</html>