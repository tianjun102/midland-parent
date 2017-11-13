<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../indexJS.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<!--列表界面-->
<div class="box">
    <section class="content">
        <div id="headIndex">
        <p class="detail-title">
            <span>买房助手>>留言管理</span>
            <%--<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>--%>
        </p>
        <form action="${ctx }/rest/leaveMsg/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <%@include file="../menu/area.jsp" %>
                <li><span>用户名称：</span>
                    <input type="text" name="userName" id="userName" placeholder="请输入用户名称"/></li>
                </li>
                <li><span>手机号码：</span>
                    <input type="text" name="phone" id="phone" placeholder="请输入手机号码"/></li>
                </li>
                <li><span>类型：</span>
                    <select name="type" id="type" class="dropdown">
                        <option value="">全部</option>
                        <c:forEach items="${leaveMsgTypes}" var="s1">
                            <option value="${s1.id}">
                                    ${s1.name}
                            </option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>邮箱：</span>
                    <input type="text" name="email" id="email" placeholder="请输入邮箱"/></li>
                </li>
                <li><span>留言时间：</span>
                    <input class="Wdate half" id="time1" style="width: 130px;" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                                             name="startTime"/> <em class="gang">-</em>
                    <input  class="Wdate half" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                        id="time2" style="width: 130px;" name="endTime"/>
                </li>
                <c:if test="${not empty isSuper}">
                    <li><span>是否删除：</span>
                        <select name="isDelete" id="isDelete" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${isDeletes}" var="s1">
                                <option value="${s1.id}" <c:if test="${s1.id==0}">selected</c:if>>
                                        ${s1.name}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                </c:if>
                <li>
                    <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                </li>
            </ul>
        </form>
        <input  onclick="batchDelete(1)" class="public_btn bg1 listButton" type="submit" value="批量删除"/>
        <c:if test="${not empty isSuper}">
            <input  onclick="batchDelete(0)" class="public_btn bg1 listButton" type="submit" value="批量恢复"/>
        </c:if>
        </div>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">

    var allHeight = $(window).height();

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '700px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/leaveMsg/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>