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


<!--委托列表界面-->
<div class="box">
    <section class="content">
        <div id="headIndex">
            <p class="detail-title">
                <span>买房助手>>问答记录列表</span>
                <c:choose>
                    <c:when test="${auditFlag==0}">
                        <a class="setup" target="contentF" onclick="openOrCloseAudit(1)">开启审核</a>
                    </c:when>
                    <c:otherwise>
                        <a class="setup" target="contentF" onclick="openOrCloseAudit(0)">关闭审核</a>
                    </c:otherwise>
                </c:choose>
            </p>

            <form action="${ctx }/rest/questions/page" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <ul class="userinfo row">
                    <%@include file="../menu/area.jsp" %>
                    <li>
                        <span>问题主题：</span>
                        <input type="text" name="questionsTitle" id="questionsTitle" placeholder="请输入问题主题"/>
                    </li>
                    <li>
                        <span>手机号码：</span>
                        <input type="text" name="questionPhone" id="questionPhone" placeholder="请输入手机号码"/>
                    </li>
                    <li>
                        <span>平台：</span>
                        <select name="source" id="source" class="dropdown">
                            <option value="">请选择</option>
                            <c:forEach items="${sources}" var="s">
                                <option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
                                        ${s.name}
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
                    <li>
                        <span>提问时间：</span>
                        <input class="Wdate half" id="time1"
                               onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                               name="startTime"/> <em class="gang">-</em><input
                            class="Wdate half"
                            onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                            id="time2" name="endTime"/></li>

                    <li>

                        <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
                </ul>
            </form>
            <input  onclick="batchDelete(1)" class="public_btn bg1 listButton" type="submit" value="批量删除"/>
            <c:if test="${not empty isSuper}">
                <input onclick="batchDelete(0)" class="public_btn bg1 listButton" type="submit" value="批量恢复"/>
            </c:if>
        </div>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    var allHeight = $(window).height();


    function openOrCloseAudit(id) {
        //0隐藏，1显示
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/open?id=" + id,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    window.location.reload();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<!-- 本页私有js -->
</body>
</html>