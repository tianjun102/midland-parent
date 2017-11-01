<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../entrust/entrustJS.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<!--委托列表界面-->
<div class="box">
    <section class="content">
        <div id="headIndex">
            <p class="detail-title">
                <span>委托管理>>买房委托列表</span>
            </p>
            <form action="${ctx }/rest/entrust/buy/page" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <ul class="userinfo row">
                    <li><span>小区名：</span><input type="text" name="communityName" id="communityName"
                                                placeholder="请输入小区名"/></li>
                    <li><span>联系人：</span><input type="text" name="nickName" id="nickName" placeholder="请输入联系人"/></li>
                    <li><span>手机号码：</span><input type="text" name="phone" id="phone" placeholder="请输入手机号码"/></li>
                    <li><span>状态：</span>
                        <select name="status" id="status" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${statusList}" var="s1">
                                <option value="${s1.id}">
                                        ${s1.name}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                    <li><span>平台：</span>
                        <select name="source" id="source" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${sources}" var="s1">
                                <option value="${s1.id}">
                                        ${s1.name}
                                </option>
                            </c:forEach>
                        </select>
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
                    <li><span>委托时间：</span><input class="Wdate half" id="time1"
                                                 onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                                                 name="startTime"/> <em class="gang">-</em><input
                            class="Wdate half"
                            onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                            id="time2" name="endTime"/></li>

                    <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
                </ul>
            </form>
            <input onclick="batchDelete(1)" class="public_btn bg1 listButton" type="submit" value="批量删除"/>
            <c:if test="${not empty isSuper}">
                <input onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/>
            </c:if>
            <input onclick="export1()" class="public_btn bg1 listButton" type="submit" value="导出"/>
        </div>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    var allHeight = $(window).height();
    var headIndex = $("#headIndex").height();

    function addEntrust() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['1000px', '750px'],
            shadeClose: false, //点击遮罩关闭
            title: ['委托'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/entrust/buy/to_add', 'no']
        });
    }


    function export1() {

        return;
        var data = $("#searchForm").serialize();

        window.location.href = "${ctx}/rest/entrust/buy/export?" + data;
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
</body>
</html>