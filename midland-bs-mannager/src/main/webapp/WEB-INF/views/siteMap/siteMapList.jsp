<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
				<th style="width: 10%">序号</th>
				<th style="width: 10%">模块名称</th>
				<th style="width: 10%">城市</th>
				<th style="width: 10%">分类</th>
                <th style="width: 40%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${xh.count}</td>
						<td>${item.modeName}</td>
						<td>${item.cityName}</td>
						<td>${item.cateName}</td>
						<td>
                            <a class="edit_img" target="contentF" onclick="to_edit(${item.id })"></a>
                            <a class="delete_img" target="contentF" onclick="delete1(${item.id })"></a>
                            <a target="contentF" onclick="sort(${item.id },${item.orderBy},1)">上移</a>
                            <a target="contentF" onclick="sort(${item.id },${item.orderBy},2)">下移</a>
                            <a href="javascript:;" target="contentF"
                               <c:if test="${item.isShow == 1}">class = "offon_img" title = "隐藏"</c:if>
                               <c:if test="${item.isShow == 0}">class = "onoff_img"  title = "显示"</c:if>
                               onclick="isOffOn(${item.id },'${item.isShow}');">
                            </a>
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

    function delete1(id){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/update?id="+id+"&isDelete=1",
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

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['450px', '500px'],
            content: ['${ctx}/rest/siteMap/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id,orderById,sort) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/sort?sort="+sort+"&orderBy="+orderById+"&id="+id,
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

    //启用关闭
    function isOffOn(id,isShow){
        if(isShow=="0"){

            layer.open({
                type: 1,
                skin: 'layer-style',
                area: ['350px','200px'],
                shadeClose: false, //点击遮罩关闭
                title:['启用'],
                resize: false,
                scrollbar:false,
                content:
                '<section class = "content" style = "border:none; height:100%;">'+
                '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否隐藏该条信息?</p>'+
                '</section>',
                btn:['确定','取消'],
                yes: function(index){
                    $.ajax({
                        type: "post",
                        url: "${ctx}/rest/siteMap/update?id="+id+"&isShow=1",
                        cache:false,
                        async:false, // 此处必须同步
                        dataType: "json",
                        success: function(xmlobj){
                            if(xmlobj.state==0){
                                layer.msg("隐藏成功！",{icon:1});
                            }
                            else{
                                layer.msg("操作失败！！",{icon:7});
                            }
                            setTimeout(function(){$("#searchForm").submit();},100);
                            layer.close(index);
                        }
                    });
                }
                ,success: function (layero) {
                    var btn = layero.find('.layui-layer-btn');
                    btn.css('text-align', 'center');
                }
            });
        }else{
            layer.open({
                type: 1,
                skin: 'layer-style',
                area: ['350px','200px'],
                shadeClose: false, //点击遮罩关闭
                title:['关闭'],
                resize: false,
                scrollbar:false,
                content:
                '<section class = "content" style = "border:none; height:100%;">'+
                '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定显示该条信息?</p>'+
                '</section>',
                btn:['确定','取消'],
                yes: function(index){
                    $.ajax({
                        type: "post",
                        url: "${ctx}/rest/siteMap/update?id="+id+"&isShow=0",
                        cache:false,
                        async:false, // 此处必须同步
                        dataType: "json",
                        success: function(xmlobj){
                            if(xmlobj.state==0){
                                layer.msg("显示成功！",{icon:1});
                            }
                            else{
                                layer.msg("操作失败！！",{icon:7});
                            }
                            setTimeout(function(){$("#searchForm").submit();},100);
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
    }


</script>
</body>
</html>