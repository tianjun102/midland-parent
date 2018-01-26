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
				<%--<th style="width: 10%">模块名称</th>--%>
				<th style="width: 10%">城市</th>
				<th style="width: 10%">分类名称</th>
				<th style="width: 10%">模块名称</th>
                <th style="width: 10%">关键字</th>
                <th style="width: 20%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count}</td>
						<td>${item.cityName}</td>
						<td>${item.cateName}</td>
                        <td>${item.modeName}</td>
                        <td>${item.name}</td>
						<td>
                            <c:if test="${item.isDelete==0}">
                            <a class="edit_img" target="contentF" onclick="to_edit(${item.id })"></a>
                            </c:if>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" title="删除" onclick="delete1(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="delete1(${item.id },0)"></a>
                            </c:if>
                            <a target="contentF" title="上移" class="up_img" onclick="sort(${item.id },${item.orderBy},1)"></a>
                            <a target="contentF" title="下移" class="down_img" onclick="sort(${item.id },${item.orderBy},2)"></a>
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
            title:['删除友情链接'],
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
                    url: "${ctx}/rest/siteMap/update?id="+id+"&isDelete="+isDelete,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(xmlobj){
                        if(xmlobj.state==0){
                            layer.msg("操作成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            layer.msg("操作失败！！",{icon:7});
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
            title: ['修改'],
            shade: 0.3,
            area: ['450px', '400px'],
            content: ['${ctx}/rest/siteMap/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id,orderById,sort) {
        var data = $("#searchForm").serialize();
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
            url: "${ctx}/rest/siteMap/batchUpdate?ids="+ids+"&isDelete="+status,
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