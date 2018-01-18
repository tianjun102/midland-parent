<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .content ul.userinfo li>span {
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb( 102, 102, 102 );
        }
        .table-add tr td a {
            display: inline-block;
            width: 35px;
            height: 20px;
            margin: 0 5px;
            background-size: contain!important;
        }
    </style>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
                <th style="width: 8%">城市</th>
                <th style="width: 8%">备案</th>
				<th style="width: 8%">创建日期</th>
				<th style="width: 8%">最近更新日期</th>
				<th style="width: 8%">显示/隐藏</th>
                <th style="width: 10%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${item.cityName}</td>
						<td>${item.record}</td>
                        <td>${item.addTime}</td>
                        <td>${item.updateTime}</td>
						<td><c:if test="${item.isShow==1}">隐藏</c:if>
                            <c:if test="${item.isShow==0}">显示</c:if>
                        </td>
						<td>
                            <a target="contentF" class="edit_img" onclick="to_edit(${item.id })" ></a>
                            <c:choose>
                                <c:when test="${item.isShow==0}">
                                    <a target="contentF" class="onoff_img" title="状态：显示" onclick="hiddenOrShow(${item.id },1)"></a>
                                </c:when>
                                <c:otherwise>
                                    <a target="contentF" class="offon_img" title="状态：隐藏" onclick="hiddenOrShow(${item.id },0)"></a>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="delete1(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="delete1(${item.id },0)"></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="14">没有找到数据!</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<!-- 分页 -->
<c:if test="${!empty paginator}">
    <c:set var="paginator" value="${paginator}"/>
    <c:set var="target" value="listDiv"/>
    <%@include file="../layout/pagination.jsp" %>
</c:if>

<script type="text/javascript">

    function hiddenOrShow(id, flag){
        //0隐藏，1显示
        $.ajax({
            type: "post",
            url: "${ctx}/rest/record/update?id="+id+"&isShow="+flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }



    function delete1(id,isDelete){
        var msg = "您确定要删除当前数据吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前数据吗？"
        }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/record/update?id="+id+"&isDelete="+isDelete,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if(data.state==0){
                    layer.msg("操作成功！",{icon:1});
                    setTimeout(function(){$("#searchForm").submit();},1000);
                }else{
                    layer.msg("操作失败！！",{icon:7});
                }
                layer.close(index);
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['100%', '100%'],
            content: ['${ctx}/rest/record/to_update?id='+id,'yes']
        });
    }


</script>
</body>
</html>