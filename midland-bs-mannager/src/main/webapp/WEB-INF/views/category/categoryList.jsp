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
            <th style="width: 8%">编号</th>
            <th style="width: 10%">父分类名称</th>
            <th style="width: 15%">分类名称</th>
            <th style="width: 10%">城市</th>
            <th style="width: 10%">类型</th>
            <th style="width: 10%">状态</th>
            <th style="width: 32%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item"
                           varStatus="xh">
                    <tr>
                        <input type="hidden" id="id" value="${item.id}"/>
                        <td>${xh.count }</td>
                        <td><c:if test="${empty item.parentName}">一级分类</c:if>${item.parentName}</td>
                        <td>${item.cateName }</td>
                        <td>${item.cityName }</td>
                        <td>
                            <c:if test="${item.type ==0}">市场调究</c:if>
                            <c:if test="${item.type ==1}">资讯</c:if>
                            <c:if test="${item.type ==2}">会员分类</c:if>
                        </td>
                        <td>
                            <c:if test="${item.isShow ==0}">开启</c:if>
                            <c:if test="${item.isShow ==1}">关闭</c:if>
                        </td>
                        <td>

                            <a target="contentF" class="edit_img" title="编辑" onclick="to_edit(${item.id })"></a>
                            <a target="contentF" class="delete_img" title="删除" onclick="delete1(${item.id })"></a>
                            <a target="contentF" onclick="sort(${item.id },${item.orderBy},1)">上移</a>
                            <a target="contentF" onclick="sort(${item.id },${item.orderBy},2)">下移</a>
                            <%--<c:if test="${item.type==3}">--%><a <c:if test="${item.isShow==0}">class="onoff_img"</c:if> <c:if test="${item.isShow==1}">class="offon_img"</c:if> target="contentF" onclick="updateCate(${item.isShow},${item.id })"></a><%--</c:if>--%>
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

        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除分类'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none; height:100%;">'+
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前分类吗?</p>'+
            '</section>',
            btn:['确定','取消'],
            yes: function(index){
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/category/delete?id="+id,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            layer.msg("删除成功！",{icon:1});
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
            area: ['420px','370px'],
            content: ['${ctx}/rest/category/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id,orderById,sort) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/sort?sort="+sort+"&orderBy="+orderById+"&id="+id,
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

    //启用，禁用
    function updateCate(isShow,id){
        if(isShow==1){
            isShow=0;
        }else if(isShow == 0){
            isShow = 1;
        }

        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/update?isShow="+isShow+"&id="+id,
            async:false, // 此处必须同步
            dataType: "json",
            data:"",
            success: function(data){

                $('#searchForm').submit();
            }
        });
    }


</script>
</body>
</html>