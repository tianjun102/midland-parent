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
                <th style="width: 8%">编号</th>
				<th style="width: 8%">城市名称</th>
                <th style="width: 8%">平台</th>
				<th style="width: 8%">页面</th>
				<th style="width: 8%">百度计量代码</th>
				<th style="width: 8%">CNZZ代码</th>
                <th style="width: 10%">操作</th>
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
                        <td>
                            <c:if test="${item.source=='0'}">网站</c:if>
                            <c:if test="${item.source=='1'}">微站</c:if>
                        </td>
						<td>
                            <c:if test="${item.model =='0'}">首页</c:if>
                            <c:if test="${item.model =='1'}">新房</c:if>
                            <c:if test="${item.model =='2'}">二手房</c:if>
                            <c:if test="${item.model =='3'}">租房</c:if>
                            <c:if test="${item.model =='4'}">写字楼</c:if>
                            <c:if test="${item.model =='5'}">商铺</c:if>
                            <c:if test="${item.model =='6'}">小区</c:if>
                        </td>
                        <td><c:if test="${item.baiduShow==1}">开启</c:if>
                            <c:if test="${item.baiduShow==0}">关闭</c:if>
                        </td>
                        <td>
                            <c:if test="${item.metaShow==0}">关闭</c:if>
                            <c:if test="${item.metaShow==1}">开启</c:if>
                        </td>
						<td>
                            <c:if test="${item.isDelete==0}">
                            <a class="edit_img" target="contentF" onclick="to_edit(${item.id})"></a>
                            </c:if>
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

    function delete1(id,isDelete){
        var msg = "您确定要删除当前分类吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前分类吗？"
        }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除页面配置'],
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
                    url: "${ctx}/rest/pageConf/update?id="+id+"&isDelete="+isDelete,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            layer.msg("操作成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
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

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['100%', '100%'],
            content: ['${ctx}/rest/pageConf/to_update?id='+id,'yes']
        });
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
            url: "${ctx}/rest/pageConf/batchUpdate?ids="+ids+"&isDelete="+status,
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