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
<div class="table-responsive m40"  style="max-height: 480px;overflow: auto;"  id='table-cont'>
<table class="table table-bordered table-add">
    <thead>
    <tr>
        <th style="width: 8%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
        <th style="width: 8%">编号</th>
        <th style="width: 8%">城市</th>
        <th style="width: 8%">区域</th>
        <th style="width: 8%">类型</th>
        <th style="width: 8%">成交套数</th>
        <th style="width: 8%">成交面积</th>
        <th style="width: 8%">成交均价</th>
        <th style="width: 8%">成交金额</th>
        <th style="width: 8%">可售套数</th>
        <th style="width: 8%">可售面积</th>
        <th style="width: 8%">数据时间</th>
        <th style="width: 8%">更新时间</th>
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
                    <td>${item.areaName}</td>
                    <td><c:forEach items="${types}" var="s">
                        <c:if test="${item.type == s.id}" >${s.name}</c:if>
                    </c:forEach></td>
                    <td>${item.dealNum}</td>
                    <td>${item.dealAcreage}</td>
                    <td>${item.price}</td>
                    <td>${item.dealPrice}</td>
                    <td>${item.soldNum}</td>
                    <td>${item.soldArea}</td>
                    <td>${item.dataTime}</td>
                    <td>${item.updateTime}</td>
                    <td>
                        <c:if test="${item.isDelete==0}">
                        <a target="contentF"  class="edit_img" title="编辑"  onclick="to_edit(${item.id })"></a>
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
<c:if test="${!empty paginator}">
    <c:set var="paginator" value="${paginator}"/>
    <c:set var="target" value="listDiv"/>
    <%@include file="../layout/pagination.jsp" %>
</c:if>
</body>

<script type="text/javascript">

    $(function () {
        var headIndex = $("#headIndex").height();
        $("#table-cont").css({maxHeight:allHeight-headIndex-100-17});
        var tableCont = document.querySelector('#table-cont');
        /**
         * scroll handle
         * @param {event} e -- scroll event
         */
        function scrollHandle (e){
            var scrollTop = this.scrollTop;
            this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
        }

        tableCont.addEventListener('scroll',scrollHandle);
    })


    function delete1(id,isDelete){
        var msg = "您确定要删除当前数据吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前数据吗？"
        }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['编辑'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none; height:100%;">' +
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">'+msg+'</p>' +
            '</section>',
            btn: ['确定', '取消'],
            yes: function (index) {
                var data = $("#formId").serialize();
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/quotation/update?id="+id+"&isDelete="+isDelete,
                    async: false, // 此处必须同步
                    dataType: "json",
                    success: function (data) {
                        if (data.state==0){
                            layer.msg("操作成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            layer.msg("操作失败！",{icon:1});
                        }
                        layer.close(index);
                    },
                    error: function () {
                        layer.msg("操作失败！", {icon: 2});
                    }
                })
            }
            , success: function (layero) {
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
            area: ['500px', '450px'],
            content: ['${ctx}/rest/quotation/to_update?id='+id,'no']
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
            url: "${ctx}/rest/quotation/batchUpdate?ids="+ids+"&isDelete="+status,
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
</html>