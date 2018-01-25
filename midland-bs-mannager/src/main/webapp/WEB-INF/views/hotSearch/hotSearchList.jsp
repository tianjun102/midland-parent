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
                <th style="width: 8%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
                <th style="width: 10%">编号</th>
                <th style="width: 10%">城市</th>
                <th style="width: 20%">热搜词</th>
				<th style="width: 10%">模块</th>
				<th style="width: 10%">平台</th>
                <th style="width: 30%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count}</td>
						<td>${item.cityName}</td>
                        <td>${item.keywords}</td>
                        <td>${item.menuName}</td>
                        <td><c:if test="${item.source==0}">网站</c:if>
                            <c:if test="${item.source==1}">微站</c:if></td>
						<td>
                            <c:if test="${item.isDelete==0}">
                            <a class="edit_img" title="编辑" target="contentF" onclick="to_edit(${item.id })"></a>
                            </c:if>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="delete1(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="delete1(${item.id },0)"></a>
                            </c:if>
                            <a target="contentF" class="up_img" title="上移" onclick="sort(${item.id },${item.orderBy},2)"></a>
                            <a target="contentF" class="down_img" title="下移" onclick="sort(${item.id },${item.orderBy},1)"></a>
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

    function delete1(id,isDelete){
        var msg = "您确定要删除当前数据吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前数据吗？"
        }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除资讯'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none; height:100%;">'+
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">'+msg+'</p>'+
            '</section>',
            btn:['确定','取消'],
            yes: function(index){
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/hotSearch/update?id="+id+"&isDelete="+isDelete,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            if(isDelete==0){
                                layer.msg("恢复成功！",{icon:1});
                            }else{
                                layer.msg("删除成功！",{icon:1});
                            }
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            if(isDelete==0){
                                layer.msg("恢复成功！",{icon:7});
                            }else{
                                layer.msg("删除成功！",{icon:7});
                            }
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

    function to_edit(id){
        debugger;
        layer.open({
            type: 2,
            title: ['修改热搜词'],
            shade: 0.3,
            area: ['500px', '350px'],
            content: ['${ctx}/rest/hotSearch/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id,orderById,sort) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotSearch/sort?sort="+sort+"&orderBy="+orderById+"&id="+id,
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

    function checkall(){
        $("input[name='pid']").each(function(){
            this.checked=true;
        });
    }



    function delcheckall(){
        $("input[name='pid']").each(function(){
            this.checked=false;
        });
    }

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });
        if(ids.length==0){
            layer.msg("请选择所操作的数据！", {icon: 2})
            return;
        }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotSearch/batchUpdate?ids="+ids+"&isDelete="+status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }


</script>
</body>
</html>