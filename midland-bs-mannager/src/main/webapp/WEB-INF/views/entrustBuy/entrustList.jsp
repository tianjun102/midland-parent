<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .table-add tr td a {
            display: inline-block;
            width: 38px;!important;
            height: 20px;
            margin: 0 5px;
            background-size: contain!important;
        }
        td
        {
            white-space: nowrap;
        }
        th
        {
            white-space: nowrap;
        }
    </style>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: auto">委托编号</th>
            <th style="width: auto">意向区域</th>
            <th style="width: auto">装修情况</th>
            <th style="width: auto">意向小区</th>
            <th style="width: auto">意向面积</th>
            <th style="width: auto">意向户型</th>
            <th style="width: auto">意向价位</th>
            <th style="width: auto">联系人</th>
            <th style="width: auto">手机号码</th>
            <th style="width: auto">备注</th>
            <th style="width: auto">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.entrusts }">
                <c:forEach items="${requestScope.entrusts }" var="item"
                           varStatus="xh">
                    <tr>
                        <td>${item.entrustSn }</td>
                        <td>${item.areaName }</td>
                        <td><c:forEach items="${decorations}" var="s">
                            <c:if test="${item.renovation == s.id}">${s.name}</c:if>
                        </c:forEach></td>

                        <td>${item.communityName }</td>
                        <td>${item.measure }</td>
                        <td>${item.layout }</td>
                        <td>${item.price }</td>
                        <td>${item.nickName }</td>
                        <td>${item.phone }</td>
                        <td>${item.remark }</td>

                        <td>
                            <c:if test="${item.resetFlag==1}">
                                <a target="contentF" onclick="toRedistribute(${item.id })">分配</a>
                            </c:if>

                            <a target="contentF" onclick="toUpdateEntrust(${item.id })">修改</a>
                            <a target="contentF" class="delete_img" onclick="deleteEntrust(${item.id })"></a>
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

    function toRedistribute(id) {
        //重新分配经纪人时，保存的地址
        var url = "${ctx}/rest/entrust/reset_agent";
        layer.open({
            type: 2,
            title: ['重新分配经纪人'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/public/toRedistribute?id=' + id+"&url="+url , 'no']
        });
    }
    function deleteEntrust(id) {
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none; height:100%;">'+
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前信息?</p>'+
            '</section>',
            btn:['确定','取消'],
            yes: function(index){
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/entrust/buy/update?id="+id+"&isDelete=1",
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(xmlobj){
                        if(xmlobj.state==0){
                            layer.msg("删除成功！",{icon:1});
                            $("#searchForm").submit();
                        }
                        if(xmlobj.state==1){
                            layer.msg("删除失败！！",{icon:7});
                        }
                        layer.close(index);
                    }
                });
            }
            ,success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });
    }


    function toUpdateEntrust(appointId) {
        layer.open({
            type: 2,
            title: ['委托详情'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/entrust/buy/to_update?entrustId=' + appointId , 'no']
        });
    }
</script>
</body>
</html>