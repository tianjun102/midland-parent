<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>美联物业 - 关于平台</title>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <style type="text/css">
        .dropdown {
            height: 38px;
            line-height: 38px;
            width: 120px !important;
            display: inline-table;
            border-width: 1px !important;
            border-style: solid !important;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-color: rgb(219, 226, 230) !important;
            border-image: initial !important;
        }
    </style>
</head>
<body>
<!--关于平台界面-->
<section class="content" style="width:auto;">
    <form action="${ctx }/rest/quotationSecondHand/toolsTip" method="POST" id="searchForm"
          onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
        <input type="hidden" name="url" id="url" value="${url}"/>
        <input type="hidden" name="showType" id="showType" value="${showType}"/>
        <ul class="userinfo row">
            <li>
                <%@include file="../quotation/dist.jsp" %>
            </li>
            <%--<li><span>区域：</span>--%>
                <%--<input type="text" name="areaName">--%>

            <%--</li>--%>
            <li>
                <span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <c:forEach items="${types}" var="type">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <span style="width: 90px">环比类型：</span>
                <select name="field" onchange="fieldChange()" id="field" class="dropdown">
                    <option value="0">套数</option>
                    <option value="1">面积</option>
                </select>
            </li>
            <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                <a   style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" class = "public_btn bg1"  href="${ctx}/rest/quotationSecondHand/index" target="contentF">返回</a></li>

        </ul>


    </form>
    <div id="listDiv"></div>

</section>
</body>
<script type="text/javascript">
    window.onload = function () {
        $('#searchForm').submit();
    }
    function fieldChange() {
        var i= $("#field").val();
        if(i==0){
            $("#showType").val("0");
            $("#url").val("dealNumContent");
        }else{
            $("#showType").val("1");
            $("#url").val("dealAcreageContent");
        }
    }
</script>
</html>

