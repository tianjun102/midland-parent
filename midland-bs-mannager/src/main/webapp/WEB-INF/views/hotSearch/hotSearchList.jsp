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
                <th style="width: 5%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
                <th style="width: 5%">编号</th>
                <th style="width: 10%">城市</th>
                <th style="width: 20%">热搜词</th>
                <th style="width: 8%;-moz-user-select:none;">热搜量&nbsp;&nbsp;<span id="sort_click" class="sort_both" ></span></th>
				<th style="width: 15%">模块</th>
                <th style="width: 5%">租售</th>
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
                        <td>${item.count}</td>
                        <td>${item.menuName}</td>
                        <td><c:if test="${item.sellRent==0}">租</c:if>
                            <c:if test="${item.sellRent==1}">售</c:if>
                        </td>
                        <td><c:if test="${item.source==0}">网站</c:if>
                            <c:if test="${item.source==1}">微站</c:if>
                        </td>
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
                            <c:choose>
                                <c:when test="${item.isShow==0}">
                                    <a target="contentF" class="onoff_img" title="状态：显示"
                                       onclick="hiddenOrShow(${item.id },1)"></a>
                                </c:when>
                                <c:otherwise>
                                    <a target="contentF" class="offon_img" title="状态：隐藏"
                                       onclick="hiddenOrShow(${item.id },0)"></a>
                                </c:otherwise>
                            </c:choose>
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
$(function () {
    if ('${sortOrder}'=='0'){
        $("#sort_click").attr("class","sort_asc");
        $("#sortOrder").val(0);
    }else if('${sortOrder}'=='1'){
        $("#sort_click").attr("class","sort_desc");
        $("#sortOrder").val(1);
    }

})
$("#sort_click").click(function () {
    if ($("#sort_click").attr("class")=="sort_both"){
        $("#sort_click").attr("class","sort_asc");
        $("#sortOrder").val(0);
        $('#searchForm').submit();
    }else if ($("#sort_click").attr("class")=="sort_asc"){
        $("#sort_click").attr("class","sort_desc");
        $("#sortOrder").val(1);
        $('#searchForm').submit();
    }else {
        $("#sort_click").attr("class","sort_asc");
        $("#sortOrder").val(0);
        $('#searchForm').submit();
    }
})


function hiddenOrShow(id, flag) {
    //0隐藏，1显示
    $.ajax({
        type: "post",
        url: "${ctx}/rest/hotSearch/update?id=" + id + "&isShow=" + flag,
        async: false, // 此处必须同步
        dataType: "json",

        success: function (data) {
            if (data.state == 0) {
                $('#searchForm').submit();
            }
        },
        error: function (data) {
            if (data.responseText != null) {
                layer.msg(data.responseText, {icon: 2});
            } else {
                layer.msg("操作失败！", {icon: 2});
            }
        }
    })
}

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
                                layer.msg("操作成功！",{icon:1});
                            }
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            if(isDelete==0){
                                layer.msg("恢复成功！",{icon:7});
                            }else{
                                layer.msg("操作成功！",{icon:7});
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
        layer.open({
            type: 2,
            title: ['修改热搜词'],
            shade: 0.3,
            area: ['500px', '500px'],
            content: ['${ctx}/rest/hotSearch/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id,orderById,sort) {
        debugger;
        if ($("#sortOrder").val()!=''){
            layer.msg("热搜量已排序,无法调整顺序,请刷新页面!", {icon: 2});
            return false;
        }
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotSearch/sort?sort="+sort+"&orderBy="+orderById+"&id="+id,
            async: false, // 此处必须同步
            dataType: "json",
            data:data,

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